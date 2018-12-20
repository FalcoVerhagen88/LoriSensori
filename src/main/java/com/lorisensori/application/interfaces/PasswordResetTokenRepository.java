package com.lorisensori.application.interfaces;

import java.time.Instant;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.domain.PasswordResetToken;


@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

	Optional<Instant> findExpiryDateByToken(String token);

	Boolean existsByToken(String token);

	Optional<Medewerker> findMedewerkerByToken(String token);

	Optional<PasswordResetToken> findByToken(String token);
}