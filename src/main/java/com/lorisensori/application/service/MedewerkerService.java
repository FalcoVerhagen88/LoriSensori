package com.lorisensori.application.service;

import com.lorisensori.application.DTOs.medewerkerDTOs.MedewerkerDTO;
import com.lorisensori.application.DTOs.tankDTOs.TankDTO;
import com.lorisensori.application.domain.Bedrijf;
import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.domain.Tank;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MedewerkerService {

    Medewerker save(Medewerker medewerker);

    boolean existsByVoornaam(String voornaam);

    List<Medewerker> findAll();

    Medewerker findById(Long id);

    Medewerker findByVoornaam(String voornaam);

    Optional<Medewerker> findByGebruikersnaam(String gebruikersnaam);

    void delete(Medewerker medewerker);

    Set<Medewerker> findByBedrijf(Bedrijf bedrijf);

    MedewerkerDTO convertToDto(Medewerker medewerker);

    Medewerker convertToEntity(MedewerkerDTO medewerkerDTO);
}
