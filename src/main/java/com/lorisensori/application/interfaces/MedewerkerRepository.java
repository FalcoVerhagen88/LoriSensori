package com.lorisensori.application.interfaces;

import com.lorisensori.application.logic.Medewerker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedewerkerRepository extends JpaRepository<Medewerker, Long> {

    boolean existsByVoornaam(String voornaam);

    Optional<Medewerker> findByVoornaam (String voornaam);
}
