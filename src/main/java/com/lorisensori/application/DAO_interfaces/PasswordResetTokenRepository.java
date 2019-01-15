package com.lorisensori.application.DAO_interfaces;

import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.domain.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;


@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Optional<Instant> findExpiryDateByToken(String token);

    Boolean existsByToken(String token);

    Optional<Medewerker> findMedewerkerByToken(String token);

    Optional<PasswordResetToken> findByToken(String token);
}