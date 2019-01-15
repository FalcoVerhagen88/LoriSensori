package com.lorisensori.application.DAO_interfaces;

import com.lorisensori.application.domain.Bedrijf;
import com.lorisensori.application.domain.Medewerker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface MedewerkerRepository extends JpaRepository<Medewerker, Long> {

    boolean existsByGebruikersnaam(String gebruikersnaam);

    Medewerker findByVoornaam(String voornaam);

    Optional<Medewerker> findByGebruikersnaam(String gebruikersnaam);

    boolean existsByEmail(String email);

    Optional<Medewerker> findByEmail(String email);

    @Override
    Medewerker getOne(Long id);

    Set<Medewerker> findByBedrijf(Bedrijf bedrijf);
}
