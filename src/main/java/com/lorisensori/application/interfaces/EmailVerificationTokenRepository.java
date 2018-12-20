package com.lorisensori.application.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.domain.token.EmailVerificationToken;


public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, Long> {

	Optional<EmailVerificationToken> findByToken(String token);

	EmailVerificationToken findByMedewerker(Medewerker medewerker);
}
