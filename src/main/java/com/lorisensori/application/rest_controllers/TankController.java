package com.lorisensori.application.rest_controllers;


import com.lorisensori.application.DTO.TankDTO;
import com.lorisensori.application.exceptions.ResourceNotFoundException;
import com.lorisensori.application.interfaces.TankRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TankController {
	private final TankRepository tankRepository;
	
	@Autowired
	public TankController(TankRepository tankRepository) {
		this.tankRepository = tankRepository;
	}
	
	//Get all Tanks
	@GetMapping("/tank/")
	public List<TankDTO> getAllTanks(){
		return tankRepository.findAll();
	}

	//Get one tank
	@GetMapping("/tank/{tankid}")
	public TankDTO getTankById(@PathVariable(value = "tankid") Long tankId) {
		return tankRepository.findById(tankId)
				.orElseThrow(() -> new ResourceNotFoundException("Tank", "tankid", tankId));
	}
	
	//Create a new Tank
	@PostMapping("/tank/")
	public TankDTO createTank(@Valid @RequestBody TankDTO tank) {
		return tankRepository.save(tank);
	}

}
