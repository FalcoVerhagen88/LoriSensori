package com.lorisensori.application.service;

import com.lorisensori.application.domain.Sensorgegevens;
import com.lorisensori.application.domain.Tank;

public interface TankService {

    Tank save(Tank tank);

    boolean existsByTanknaam(String tanknaam);

    Iterable<Tank> findAll();

    Tank findByTanknaam(String tanknaam);

    Tank findByTankId(Long id);
    
    Tank findByDevId(String devId);
    
    void saveSensorgegevens(Sensorgegevens sensorgegevens);

    void delete(Tank tank);
}
