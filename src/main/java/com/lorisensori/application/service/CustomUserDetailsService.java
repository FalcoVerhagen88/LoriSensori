package com.lorisensori.application.service;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lorisensori.application.domain.CustomUserDetails;
import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.interfaces.MedewerkerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MedewerkerRepository medewerkerRepository;

	private static final Logger logger = Logger.getLogger(CustomUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String gebruikersnaam) throws UsernameNotFoundException {
		Optional<Medewerker> dbUser = medewerkerRepository.findByGebruikersnaam(gebruikersnaam);
		logger.info("Got user: " + dbUser + " for " + gebruikersnaam);
		return dbUser.map(CustomUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("Couldn't find a matching gebruikersnaam in the " +
						"database for " + gebruikersnaam));
	}

	public UserDetails loadUserById(Long id) {
		Optional<Medewerker> dbUser = medewerkerRepository.findById(id);
		logger.info("Got user: " + dbUser + " for " + id);
		return dbUser.map(CustomUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("Couldn't find a matching id in the " +
						"database for " + id));
	}
}
