package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.DAO_interfaces.SensorgegevensRepository;
import com.lorisensori.application.domain.Sensorgegevens;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SensorgegevensController {

    private final SensorgegevensRepository sensorgegevensRepository;

    public SensorgegevensController(SensorgegevensRepository sensorgegevensRepository) {
        this.sensorgegevensRepository = sensorgegevensRepository;
    }

    @GetMapping("/sensorgegevens")
    public Iterable<Sensorgegevens> getAllSensorgegevens() {
        return sensorgegevensRepository.findAll();
    }
}
