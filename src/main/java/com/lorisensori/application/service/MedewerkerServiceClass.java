package com.lorisensori.application.service;

import com.lorisensori.application.DAO_interfaces.MedewerkerRepository;
import com.lorisensori.application.domain.CustomUserDetails;
import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.domain.Recht;
import com.lorisensori.application.domain.UserDevice;
import com.lorisensori.application.domain.payload.LogOutRequest;
import com.lorisensori.application.domain.payload.RegistrationRequest;
import com.lorisensori.application.domain.token.RefreshToken;
import com.lorisensori.application.enums.RechtEnums;
import com.lorisensori.application.exceptions.UserLogoutException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MedewerkerServiceClass {

    private final PasswordEncoder passwordEncoder;

    private final MedewerkerRepository medewerkerRepository;

    private final RechtService rechtService;

    private final UserDeviceService userDeviceService;

    private final RefreshTokenService refreshTokenService;

    private static final Logger logger = Logger.getLogger(MedewerkerServiceClass.class);

    @Autowired
    public MedewerkerServiceClass(PasswordEncoder passwordEncoder, MedewerkerRepository medewerkerRepository, RechtService rechtService, UserDeviceService userDeviceService, RefreshTokenService refreshTokenService) {
        this.passwordEncoder = passwordEncoder;
        this.medewerkerRepository = medewerkerRepository;
        this.rechtService = rechtService;
        this.userDeviceService = userDeviceService;
        this.refreshTokenService = refreshTokenService;
    }

    /**
     * Finds a user in the database by username
     */
    public Optional<Medewerker> findByGebruikersnaam(String username) {
        return medewerkerRepository.findByGebruikersnaam(username);
    }

    /**
     * Finds a user in the database by email
     */
    public Optional<Medewerker> findByEmail(String email) {
        return medewerkerRepository.findByEmail(email);
    }

    /**
     * Finds current logged in user in the database by email Note: This will always
     * return a concrete valid instance.
     */
    public Medewerker getLoggedInUser(String email) {
        return findByEmail(email).get();
    }

    /**
     * Find a user in db by id.
     */
    public Optional<Medewerker> findByMedewerkerId(Long MedewerkerId) {
        return medewerkerRepository.findById(MedewerkerId);
    }

    /**
     * Save the user to the database
     */
    public Medewerker save(Medewerker medewerker) {
        return medewerkerRepository.save(medewerker);
    }

    /**
     * Check is the user exists given the email: naturalId
     */
    public Boolean existsByEmail(String email) {
        return medewerkerRepository.existsByEmail(email);
    }

    /**
     * Check is the user exists given the username: naturalId
     */
    public Boolean existsByGebruikersnaam(String gebruikersnaam) {
        return medewerkerRepository.existsByGebruikersnaam(gebruikersnaam);
    }


    /**
     * Creates a new user from the registration request
     */
    public Medewerker createMedewerker(RegistrationRequest registerRequest) {
        Medewerker newUser = new Medewerker();
        Boolean isNewUserAsAdmin = registerRequest.getRegisterAsAdmin();
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setGebruikersnaam(registerRequest.getUsername());
        newUser.addRechten(getRechtenForNewMedewerker(isNewUserAsAdmin));
        newUser.setActive(true);
        newUser.setEmailVerified(false);
        newUser.setAchternaam("testachternaam2");
        newUser.setTelefoonnummer("01234567892");
        newUser.setVoornaam("testvoornaam2");

        return newUser;
    }

    /**
     * Performs a quick check to see what roles the new user could benefit from
     *
     * @return list of roles for the new user
     */
    private Set<Recht> getRechtenForNewMedewerker(Boolean isAdmin) {
        Set<Recht> newUserRoles = new HashSet<>(rechtService.findAll());
        Recht adminRole = new Recht(RechtEnums.ROLE_ADMIN);
        if (!isAdmin) {
            newUserRoles.remove(adminRole);
        }
        logger.info("Setting user roles: " + newUserRoles);
        return newUserRoles;
    }

    /**
     * Log the given user out and delete the refresh token associated with it. If no device
     * is associated with it, fail silently.
     */
    public void logoutUser(CustomUserDetails customUserDetails, LogOutRequest logOutRequest) {
        String deviceId = logOutRequest.getDeviceInfo().getDeviceId();
        Optional<UserDevice> userDeviceOpt = userDeviceService.findByDeviceId(deviceId);
        userDeviceOpt.orElseThrow(() -> new UserLogoutException(logOutRequest.getDeviceInfo().getDeviceId(), "" +
                "Invalid device Id supplied. No matching user device found"));
        logger.info("Removing refresh token associated with device [" + userDeviceOpt + "]");
        userDeviceOpt.map(UserDevice::getRefreshToken)
                .map(RefreshToken::getId)
                .ifPresent(refreshTokenService::deleteById);
    }
}
