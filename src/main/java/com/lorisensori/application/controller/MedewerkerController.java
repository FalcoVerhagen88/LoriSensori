package com.lorisensori.application.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lorisensori.application.annotations.CurrentUser;
import com.lorisensori.application.domain.CustomUserDetails;
import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.domain.payload.ApiResponse;
import com.lorisensori.application.domain.payload.LogOutRequest;
import com.lorisensori.application.domain.payload.UpdatePasswordRequest;
import com.lorisensori.application.event.OnUserAccountChangeEvent;
import com.lorisensori.application.exceptions.UpdatePasswordException;
import com.lorisensori.application.service.AuthService;
import com.lorisensori.application.service.MedewerkerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/user")
@Api(value = "User Rest API", description = "Defines endpoints for the logged in user. It's " +
		"secured by default ")


public class MedewerkerController {

	private static final Logger logger = Logger.getLogger(MedewerkerController.class);

	@Autowired
	private AuthService authService;

	@Autowired
	private MedewerkerService userService;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	/**
	 * Gets the current user profile of the logged in user
	 */
	@GetMapping("/me")
	@PreAuthorize("hasRole('USER')")
	@ApiOperation(value = "Returns the current user profile")
	public ResponseEntity<?> getUserProfile(@CurrentUser CustomUserDetails currentUser) {
		logger.info("Inside secured resource with user");
		logger.info(currentUser.getGebruikersnaam() + " has role: " + currentUser.getRechten());
		return ResponseEntity.ok("Hello. This is about me");
	}

	/**
	 * Returns all admins in the system. Requires Admin access
	 */
	@GetMapping("/admins")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "Returns the list of configured admins. Requires ADMIN Access")
	public ResponseEntity<?> getAllAdmins() {
		logger.info("Inside secured resource with admin");
		return ResponseEntity.ok("Hello. This is about admins");
	}


	/**
	 * Updates the password of the current logged in user
	 */
	@PostMapping("/password/update")
	@PreAuthorize("hasRole('USER')")
	@ApiOperation(value = "Allows the user to change his password once logged in by supplying the correct current " +
			"password")
	public ResponseEntity<?> updateUserPassword(@CurrentUser CustomUserDetails customUserDetails,
			@ApiParam(value = "The UpdatePasswordRequest payload") @Valid @RequestBody UpdatePasswordRequest updatePasswordRequest) {
		Medewerker updatedUser = authService.updatePassword(customUserDetails, updatePasswordRequest)
				.orElseThrow(() -> new UpdatePasswordException("--Empty--", "No such user present."));

		OnUserAccountChangeEvent onUserPasswordChangeEvent =
				new OnUserAccountChangeEvent(updatedUser, "Update Password", "Change successful");
		applicationEventPublisher.publishEvent(onUserPasswordChangeEvent);

		return ResponseEntity.ok(new ApiResponse("Password changed successfully", true));
	}

	/**
	 * Log the user out from the app/device. Release the refresh token associated with the
	 * user device.
	 */
	@PostMapping("/logout")
	@ApiOperation(value = "Logs the specified user device and clears the refresh tokens associated with it")
	public ResponseEntity<?> logoutUser(@CurrentUser CustomUserDetails customUserDetails,
			@ApiParam(value = "The LogOutRequest payload") @Valid @RequestBody LogOutRequest logOutRequest) {
		userService.logoutUser(customUserDetails, logOutRequest);
		return ResponseEntity.ok(new ApiResponse("Log out successful", true));
	}
}
