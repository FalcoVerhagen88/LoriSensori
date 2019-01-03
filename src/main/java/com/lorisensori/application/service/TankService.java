package com.lorisensori.application.service;

import com.lorisensori.application.DTOs.tankDTOs.TankDTO;
import com.lorisensori.application.domain.Tank;

import java.util.List;

public interface TankService {

    Tank save(Tank tank);

    boolean existsByTanknaam(String tanknaam);

    List<TankDTO> findAll();

    Tank findByTanknaam(String tanknaam);

    Tank findByTankId(Long id);

    void delete(Tank tank);
}
