package com.lorisensori.application.service;

import com.lorisensori.application.DTOs.medewerkerDTOs.MedewerkerDTO;
import com.lorisensori.application.DTOs.medewerkerDTOs.UpdateMedewerkerDTO;
import com.lorisensori.application.domain.Medewerker;

import java.util.List;

public interface MedewerkerService {

    MedewerkerDTO create(MedewerkerDTO medewerkerDTO);

    Medewerker save(Medewerker medewerker);
    boolean existsByVoornaam(String voornaam);
    List<MedewerkerDTO> findAll();
    Medewerker findById(Long id);
    Medewerker findByVoornaam(String voornaam);
    UpdateMedewerkerDTO update(Long id, UpdateMedewerkerDTO updateMedewerkerDTO);
    void delete(Long id);
}
