package com.lorisensori.application.service;

import java.util.Set;

import com.lorisensori.application.domain.Sensorgegevens;
import com.lorisensori.application.domain.Tank;

public interface SensorgegevensService {

    Sensorgegevens save(Sensorgegevens sensorgegevens);

    Iterable<Sensorgegevens> findAll();

	Set<Sensorgegevens> findByTank(Tank tank);
}
