package com.lorisensori.application.service;

import java.util.List;
import java.util.Set;

import com.lorisensori.application.domain.Bedrijf;
import com.lorisensori.application.domain.Tank;

public interface TankService {

    Tank save(Tank tank);

    boolean existsByTanknaam(String tanknaam);

    List<Tank> findAll();
    
    Set<Tank> findByBedrijf(Bedrijf bedrijf);

    Tank findByTanknaam(String tanknaam);

    Tank findByTankId(Long id);

    void delete(Tank tank);
    
}
