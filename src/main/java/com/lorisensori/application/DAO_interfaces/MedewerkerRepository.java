package com.lorisensori.application.DAO_interfaces;

import com.lorisensori.application.domain.Medewerker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedewerkerRepository extends JpaRepository<Medewerker, Long> {

    Medewerker findByVoornaam(String voornaam);

    Optional<Medewerker> findByGebruikersnaam(String gebruikersnaam);

}
