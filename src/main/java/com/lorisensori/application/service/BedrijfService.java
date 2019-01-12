package com.lorisensori.application.service;

import com.lorisensori.application.domain.Bedrijf;

import java.util.List;


public interface BedrijfService {

    Bedrijf save(Bedrijf bedrijf);

    boolean existsByBedrijfsnaam(String bedrijfsnaam);

    List<Bedrijf> findAll();

    Bedrijf findByBedrijfsnaam(String bedrijfsnaam);

    void delete(Bedrijf bedrijf);
}
