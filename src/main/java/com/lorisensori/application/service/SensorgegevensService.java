package com.lorisensori.application.service;

import com.lorisensori.application.domain.Sensorgegevens;

public interface SensorgegevensService {

    Sensorgegevens save(Sensorgegevens sensorgegevens);

    Iterable<Sensorgegevens> findAll();
}
