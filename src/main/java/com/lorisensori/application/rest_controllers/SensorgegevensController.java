package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.interfaces.SensorgegevensRepository;
import com.lorisensori.application.logic.Sensorgegevens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SensorgegevensController {
	
	private final SensorgegevensRepository sensorgegevensRepository;
	
	@Autowired
	public SensorgegevensController(SensorgegevensRepository sensorgegevensRepository) {
		this.sensorgegevensRepository = sensorgegevensRepository;
	}
	
	@GetMapping("/sensorgegevens")
	public List<Sensorgegevens> getAllSensorgegevens(){
		return sensorgegevensRepository.findAll();
		
		
	}
}
