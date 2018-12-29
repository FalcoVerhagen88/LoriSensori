package com.lorisensori.application.service;

import com.lorisensori.application.DAO_interfaces.PasswordResetTokenRepository;
import com.lorisensori.application.domain.PasswordResetToken;
import com.lorisensori.application.exceptions.InvalidTokenRequestException;
import com.lorisensori.application.util.Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;



@Service
public class PasswordResetTokenService {

	final
	PasswordResetTokenRepository passwordResetTokenRepository;

	private static final Logger logger = Logger.getLogger(PasswordResetTokenService.class);

	@Value("${app.token.password.reset.duration}")
	private Long expiration;

	final
	MedewerkerServiceClass userService;

	@Autowired
	public PasswordResetTokenService(PasswordResetTokenRepository passwordResetTokenRepository, MedewerkerServiceClass userService) {
		this.passwordResetTokenRepository = passwordResetTokenRepository;
		this.userService = userService;
	}

	/**
	 * Saves the given password reset token
	 */
	public PasswordResetToken save(PasswordResetToken passwordResetToken) {
		return passwordResetTokenRepository.save(passwordResetToken);
	}

	/**
	 * Finds a token in the database given its naturalId
	 */
	public Optional<PasswordResetToken> findByToken(String token) {
		return passwordResetTokenRepository.findByToken(token);
	}

	/**
	 * Creates and returns a new password token to which a user must be associated
	 */
	public PasswordResetToken createToken() {
		PasswordResetToken passwordResetToken = new PasswordResetToken();
		String token = Util.generateRandomUuid();
		passwordResetToken.setToken(token);
		passwordResetToken.setExpiryDate(Instant.now().plusMillis(expiration));
		return passwordResetToken;
	}

	/**
	 * Verify whether the token provided has expired or not on the basis of the current
	 * server time and/or throw error otherwise
	 */
	public void verifyExpiration(PasswordResetToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			throw new InvalidTokenRequestException("Password Reset Token", token.getToken(),
					"Expired token. Please issue a new request");
		}
	}
}