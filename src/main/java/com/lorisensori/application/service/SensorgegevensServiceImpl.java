package com.lorisensori.application.service;

import com.lorisensori.application.DAO_interfaces.SensorgegevensRepository;
import com.lorisensori.application.domain.Sensorgegevens;
import org.springframework.stereotype.Service;

//Business logic goes here NOT in the repository
@Service("sensorgegevensService")
public class SensorgegevensServiceImpl implements SensorgegevensService {

    private final SensorgegevensRepository sensorgegevensRepository;

    public SensorgegevensServiceImpl(SensorgegevensRepository sensorgegevensRepository) {
        this.sensorgegevensRepository = sensorgegevensRepository;
    }

    @Override
    public Sensorgegevens save(Sensorgegevens sensorgegevens) {
        return sensorgegevensRepository.save(sensorgegevens);
    }

    @Override
    public Iterable<Sensorgegevens> findAll() {
        return sensorgegevensRepository.findAll();
    }
}
