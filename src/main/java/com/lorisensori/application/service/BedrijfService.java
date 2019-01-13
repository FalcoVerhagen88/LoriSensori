package com.lorisensori.application.service;

import com.lorisensori.application.DTOs.bedrijfDTOs.BedrijfDTO;
import com.lorisensori.application.domain.Bedrijf;
import org.springframework.stereotype.Service;


public interface BedrijfService {

    Bedrijf save(Bedrijf bedrijf);

    boolean existsByBedrijfsnaam(String bedrijfsnaam);

    Iterable<Bedrijf> findAll();

    Bedrijf findByBedrijfsnaam(String bedrijfsnaam);

    void delete(Bedrijf bedrijf);

    BedrijfDTO convertToDto(Bedrijf bedrijf);

    Bedrijf convertToEntity(BedrijfDTO bedrijfDTO);
}
