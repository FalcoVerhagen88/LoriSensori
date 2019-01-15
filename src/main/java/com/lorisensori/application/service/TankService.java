package com.lorisensori.application.service;


import com.lorisensori.application.DTOs.tankDTOs.TankBedrijfDTO;
import com.lorisensori.application.DTOs.tankDTOs.TankDTO;
import com.lorisensori.application.domain.Sensorgegevens;

import java.util.List;
import java.util.Set;

import com.lorisensori.application.domain.Bedrijf;
import com.lorisensori.application.domain.Tank;

public interface TankService {

    Tank save(Tank tank);

    boolean existsByTanknaam(String tanknaam);

    List<Tank> findAll();

    Set<Tank> findByBedrijf(Bedrijf bedrijf);

    Tank findByTankId(Long id);

    Tank findByDevId(String devId);

    void saveSensorgegevens(Sensorgegevens sensorgegevens);

    void delete(Tank tank);

    TankDTO convertToDto(Tank tank);

    Tank convertToEntity(TankDTO tankDTO);

    TankBedrijfDTO convertToTankBedrijfDTO(Tank tank);
}
