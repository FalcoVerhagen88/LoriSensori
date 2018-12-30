package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.service.SensorgegevensService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SensorgegevensController {

    private final SensorgegevensService sensorgegevensService;

    public SensorgegevensController(SensorgegevensService sensorgegevensService) {
        this.sensorgegevensService = sensorgegevensService;
    }

}
