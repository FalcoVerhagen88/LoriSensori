package com.lorisensori.application.DAO_interfaces;

import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.domain.token.EmailVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, Long> {

	Optional<EmailVerificationToken> findByToken(String token);

	EmailVerificationToken findByMedewerker(Medewerker medewerker);
}
