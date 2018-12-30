package com.lorisensori.application.service;

import com.lorisensori.application.domain.CustomUserDetails;
import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.domain.PasswordResetToken;
import com.lorisensori.application.domain.UserDevice;
import com.lorisensori.application.domain.payload.*;
import com.lorisensori.application.domain.token.EmailVerificationToken;
import com.lorisensori.application.domain.token.RefreshToken;
import com.lorisensori.application.exceptions.*;
import com.lorisensori.application.security.JwtTokenProvider;
import com.lorisensori.application.util.Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
public class AuthService {

	private final MedewerkerServiceClass medewerkerService;

	private final RechtService rechtService;

	private final JwtTokenProvider tokenProvider;

	private final RefreshTokenService refreshTokenService;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	private final EmailVerificationTokenService emailVerificationTokenService;

	private final UserDeviceService userDeviceService;

	private final PasswordResetTokenService passwordResetTokenService;

	private static final Logger logger = Logger.getLogger(AuthService.class);

	@Autowired
	public AuthService(MedewerkerServiceClass medewerkerService, RechtService rechtService, JwtTokenProvider tokenProvider, RefreshTokenService refreshTokenService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, EmailVerificationTokenService emailVerificationTokenService, UserDeviceService userDeviceService, PasswordResetTokenService passwordResetTokenService) {
		this.medewerkerService = medewerkerService;
		this.rechtService = rechtService;
		this.tokenProvider = tokenProvider;
		this.refreshTokenService = refreshTokenService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.emailVerificationTokenService = emailVerificationTokenService;
		this.userDeviceService = userDeviceService;
		this.passwordResetTokenService = passwordResetTokenService;
	}

	/**
	 * Registers a new user in the database by performing a series of quick checks.
	 * @return A user object if successfully created
	 */
	public Optional<Medewerker> registerMedewerker(RegistrationRequest newRegistrationRequest) {
		String newRegistrationRequestEmail = newRegistrationRequest.getEmail();
		if (emailAlreadyExists(newRegistrationRequestEmail)) {
			logger.error("Email already exists: " + newRegistrationRequestEmail);
			throw new ResourceAlreadyInUseException("Email", "Address", newRegistrationRequestEmail);
		}
		logger.info("Trying to register new user [" + newRegistrationRequestEmail + "]");
		Medewerker newUser = medewerkerService.createMedewerker(newRegistrationRequest);
		Medewerker registeredNewUser = medewerkerService.save(newUser);
		return Optional.ofNullable(registeredNewUser);
	}

	/**
	 * Checks if the given email already exists in the database repository or not
	 * @return true if the email exists else false
	 */
	public Boolean emailAlreadyExists(String email) {
		return medewerkerService.existsByEmail(email);
	}

	/**
	 * Checks if the given email already exists in the database repository or not
	 * @return true if the email exists else false
	 */
	public Boolean usernameAlreadyExists(String username) {
		return medewerkerService.existsByGebruikersnaam(username);
	}

	/**
	 * Authenticate user and log them in given a loginRequest
	 *
	 */
	
	//:TODO nu controleert hij op gebruikersnaam bij inloggen
	public Optional<Authentication> authenticateUser(LoginRequest loginRequest) {
		return Optional.ofNullable(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
				loginRequest.getPassword())));
	}

	/**
	 * Confirms the user verification based on the token expiry and mark the user as active.
	 * If user is already verified, save the unnecessary database calls.
	 */
	public Optional<Medewerker> confirmEmailRegistration(String emailToken) {
		Optional<EmailVerificationToken> emailVerificationTokenOpt =
				emailVerificationTokenService.findByToken(emailToken);
		emailVerificationTokenOpt.orElseThrow(() ->
				new ResourceNotFoundException("Token", "Email verification", emailToken));

		Optional<Medewerker> registeredUserOpt = emailVerificationTokenOpt.map(EmailVerificationToken::getMedewerker);

		Boolean isUserAlreadyVerified = emailVerificationTokenOpt.map(EmailVerificationToken::getMedewerker)
				.map(Medewerker::getEmailVerified).filter(Util::isTrue).orElse(false);

		if (isUserAlreadyVerified) {
			logger.info("User [" + emailToken + "] already registered.");
			return registeredUserOpt;
		}
		emailVerificationTokenOpt.ifPresent(emailVerificationTokenService::verifyExpiration);
		emailVerificationTokenOpt.ifPresent(EmailVerificationToken::confirmStatus);
		emailVerificationTokenOpt.ifPresent(emailVerificationTokenService::save);
		registeredUserOpt.ifPresent(Medewerker::confirmVerificiation);
		registeredUserOpt.ifPresent(medewerkerService::save);
		return registeredUserOpt;
	}

	/**
	 * Attempt to regenerate a new email verification token given a valid
	 * previous expired token. If the previous token is valid, increase its expiry
	 * else update the token value and add a new expiration.
	 */
	public Optional<EmailVerificationToken> recreateRegistrationToken(String existingToken) {
		Optional<EmailVerificationToken> emailVerificationTokenOpt =
				emailVerificationTokenService.findByToken(existingToken);
		emailVerificationTokenOpt.orElseThrow(() ->
				new ResourceNotFoundException("Token", "Existing email verification", existingToken));
		Boolean userAlreadyVerified =
				emailVerificationTokenOpt.map(EmailVerificationToken::getMedewerker)
						.map(Medewerker::getEmailVerified).filter(Util::isTrue).orElse(false);
		if (userAlreadyVerified) {
			return Optional.empty();
		}
		return emailVerificationTokenOpt.map(emailVerificationTokenService::updateExistingTokenWithNameAndExpiry);
	}

	/**
	 * Validates the password of the current logged in user with the given password
	 */
	public Boolean currentPasswordMatches(Medewerker currentUser, String password) {
		return passwordEncoder.matches(password, currentUser.getPassword());
	}

	/**
	 * Updates the password of the current logged in user
	 */
	public Optional<Medewerker> updatePassword(CustomUserDetails customUserDetails,
			UpdatePasswordRequest updatePasswordRequest) {
		Medewerker currentUser = medewerkerService.getLoggedInUser(customUserDetails.getEmail());

		if (!currentPasswordMatches(currentUser, updatePasswordRequest.getOldPassword())) {
			logger.info("Current password is invalid for [" + currentUser.getPassword() + "]");
			throw new UpdatePasswordException(currentUser.getEmail(), "Invalid current password");
		}
		String newPassword = passwordEncoder.encode(updatePasswordRequest.getNewPassword());
		currentUser.setPassword(newPassword);
		medewerkerService.save(currentUser);
		return Optional.ofNullable(currentUser);
	}

	/**
	 * Generates a JWT token for the validated client
	 */
	public String generateToken(CustomUserDetails customUserDetails) {
		return tokenProvider.generateToken(customUserDetails);
	}

	/**
	 * Generates a JWT token for the validated client by userId
	 */
	public String generateTokenFromUserId(Long userId) {
		return tokenProvider.generateTokenFromUserId(userId);
	}

	/**
	 * Creates and persists the refresh token for the user device. If device exists
	 * already, we don't care. Unused devices with expired tokens should be cleaned
	 * with a cron job. The generated token would be encapsulated within the jwt.
	 */
	public Optional<RefreshToken> createAndPersistRefreshTokenForDevice(Authentication authentication,
			LoginRequest loginRequest) {
		Medewerker currentUser = (Medewerker) authentication.getPrincipal();
		RefreshToken refreshToken = refreshTokenService.createRefreshToken();
		UserDevice userDevice = userDeviceService.createUserDevice(loginRequest.getDeviceInfo());
		userDevice.setMedewerker(currentUser);
		userDevice.setRefreshToken(refreshToken);
		refreshToken.setUserDevice(userDevice);
		refreshToken = refreshTokenService.save(refreshToken);
		return Optional.ofNullable(refreshToken);
	}

	/**
	 * Refresh the expired jwt token using a refresh token and device info. The
	 * * refresh token is mapped to a specific device and if it is unexpired, can help
	 * * generate a new jwt. If the refresh token is inactive for a device or it is expired,
	 * * throw appropriate errors.
	 */
	public Optional<String> refreshJwtToken(TokenRefreshRequest tokenRefreshRequest) {
		//tokenFromDb's device info should match this one
		String requestRefreshToken = tokenRefreshRequest.getRefreshToken();
		Optional<RefreshToken> refreshTokenOpt =
				refreshTokenService.findByToken(requestRefreshToken);
		refreshTokenOpt.orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "Missing refresh token in " +
				"database. Please login again"));

		refreshTokenOpt.ifPresent(refreshTokenService::verifyExpiration);
		refreshTokenOpt.ifPresent(userDeviceService::verifyRefreshAvailability);
		refreshTokenOpt.ifPresent(refreshTokenService::increaseCount);
		return refreshTokenOpt.map(RefreshToken::getUserDevice)
				.map(UserDevice::getMedewerker)
				.map(Medewerker::getId).map(this::generateTokenFromUserId);
	}

	/**
	 * Generates a password reset token from the given reset request
	 */
	public Optional<PasswordResetToken> generatePasswordResetToken(PasswordResetLinkRequest passwordResetLinkRequest) {
		String email = passwordResetLinkRequest.getEmail();
		Optional<Medewerker> userOpt = medewerkerService.findByEmail(email);
		userOpt.orElseThrow(() -> new PasswordResetLinkException(email, "No matching user found for the given " +
				"request"));
		PasswordResetToken passwordResetToken = passwordResetTokenService.createToken();
		userOpt.ifPresent(passwordResetToken::setMedewerker);
		passwordResetTokenService.save(passwordResetToken);
		return Optional.ofNullable(passwordResetToken);
	}

	/**
	 * Reset a password given a reset request and return the updated user
	 */
	public Optional<Medewerker> resetPassword(PasswordResetRequest passwordResetRequest) {
		String token = passwordResetRequest.getToken();
		Optional<PasswordResetToken> passwordResetTokenOpt = passwordResetTokenService.findByToken(token);
		passwordResetTokenOpt.orElseThrow(() -> new ResourceNotFoundException("Password Reset Token", "Token Id",
				token));

		passwordResetTokenOpt.ifPresent(passwordResetTokenService::verifyExpiration);
		final String encodedPassword = passwordEncoder.encode(passwordResetRequest.getPassword());

		Optional<Medewerker> userOpt = passwordResetTokenOpt.map(PasswordResetToken::getMedewerker);
		userOpt.ifPresent(user -> user.setPassword(encodedPassword));
		userOpt.ifPresent(medewerkerService::save);
		return userOpt;
	}
}