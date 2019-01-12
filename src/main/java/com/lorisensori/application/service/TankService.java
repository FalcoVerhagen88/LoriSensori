package com.lorisensori.application.service;


import com.lorisensori.application.DTOs.tankDTOs.TankDTO;
import com.lorisensori.application.domain.Bedrijf;
import com.lorisensori.application.domain.Sensorgegevens;
import com.lorisensori.application.domain.Tank;

import java.util.List;
import java.util.Set;

public interface TankService {

    Tank save(Tank tank);

    boolean existsByTanknaam(String tanknaam);

    List<Tank> findAll();
    
    Set<Tank> findByBedrijf(Bedrijf bedrijf);

    Tank findByTanknaam(String tanknaam);

    Tank findByTankId(Long id);
    
    Tank findByDevId(String devId);
    
    void saveSensorgegevens(Sensorgegevens sensorgegevens);

    void delete(Tank tank);

    TankDTO updateTank(TankDTO tankDTO);

    TankDTO convertToDto(Tank tank);
    Tank convertToEntity(TankDTO tankDTO);
    
}
