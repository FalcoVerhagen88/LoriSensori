package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.DTOs.tankDTOs.SensorgegevensDTO;
import com.lorisensori.application.domain.Sensorgegevens;
import com.lorisensori.application.service.SensorgegevensService;
import com.lorisensori.application.service.TankService;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SensorgegevensController {

    @Autowired
	private SensorgegevensService sensorgegevensService;
    
    @Autowired
    private TankService tankService;
    
    @Autowired
    private ModelMapper modelMapper;


    
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/tank/sensorgegevens/{tankid}")
	public Set<SensorgegevensDTO> getAllSensorgegeven(@PathVariable(value = "tankId") Long tankId) {
		Set<Sensorgegevens> sensorgegevens = sensorgegevensService.findByTank(tankService.findByTankId(tankId));
		return sensorgegevens.stream().map(sensorgegeven -> convertToDto(sensorgegeven)).collect(Collectors.toSet());
	}
		
		
	
	
	private SensorgegevensDTO convertToDto(Sensorgegevens sensorgegevens) {
		SensorgegevensDTO sensorgegevensDTO = modelMapper.map(sensorgegevens, SensorgegevensDTO.class);
		return sensorgegevensDTO;
	}
	
	private Sensorgegevens convertToEntity(SensorgegevensDTO sensorgegevensDTO) {
		Sensorgegevens sensorgegevens = modelMapper.map(sensorgegevensDTO, Sensorgegevens.class);
		return sensorgegevens;
	}
	
}
