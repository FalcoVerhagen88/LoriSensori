package com.lorisensori.application.service;

import com.lorisensori.application.DTOs.medewerkerDTOs.MedewerkerDTO;
import com.lorisensori.application.domain.Medewerker;

import java.util.List;
import java.util.Optional;

public interface MedewerkerService {

    Medewerker save(Medewerker medewerker);
    boolean existsByVoornaam(String voornaam);
    List<MedewerkerDTO> findAll();
    Medewerker findById(Long id);
    Medewerker findByVoornaam(String voornaam);
    Optional<Medewerker> findByGebruikersnaam(String gebruikersnaam);
    void delete(Medewerker medewerker);
}
