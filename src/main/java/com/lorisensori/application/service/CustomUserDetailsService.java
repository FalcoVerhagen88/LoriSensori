package com.lorisensori.application.service;

import com.lorisensori.application.DAO_interfaces.MedewerkerRepository;
import com.lorisensori.application.domain.CustomUserDetails;
import com.lorisensori.application.domain.Medewerker;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MedewerkerRepository medewerkerRepository;

    private static final Logger logger = Logger.getLogger(CustomUserDetailsService.class);

    @Autowired
    public CustomUserDetailsService(MedewerkerRepository medewerkerRepository) {
        this.medewerkerRepository = medewerkerRepository;
    }

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
