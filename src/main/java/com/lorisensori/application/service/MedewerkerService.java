package com.lorisensori.application.service;

import com.lorisensori.application.domain.Medewerker;

import java.util.Optional;

public interface MedewerkerService {

    Medewerker save (Medewerker medewerker);

    boolean existsByVoornaam(String voornaam);

    Iterable<Medewerker> findAll();

    Optional<Medewerker> findById(Long id);

    Medewerker findByVoornaam(String voornaam);

    Medewerker delete(Medewerker medewerker);
}
