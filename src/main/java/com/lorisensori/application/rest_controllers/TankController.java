package com.lorisensori.application.rest_controllers;


import com.lorisensori.application.logic.Bedrijf;
import com.lorisensori.application.logic.Medewerker;
import com.lorisensori.application.logic.Tank;
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
	public List<Tank> getAllTanks(){
		return tankRepository.findAll();
	}

	//Get one tank
	@GetMapping("/tank/{tankid}")
	public Tank getTankById(@PathVariable(value = "tankid") Long tankId) {
		return tankRepository.findById(tankId)
				.orElseThrow(() -> new ResourceNotFoundException("Tank", "tankid", tankId));
	}
	
	//Create a new Tank
	@PostMapping("/tank/")
	public Tank createTank(@Valid @RequestBody Tank tank) {
		return tankRepository.save(tank);
	}

	//Add tankbeheerders (medewerkers)
	@PutMapping("/tank/addtankbeheerder/{tankId}")
	public Tank addTankBeheerder(@PathVariable(value = "tankId") Long tankId, @Valid @RequestBody Medewerker medewerkerDetails) {
		Tank tank = tankRepository.findById(tankId)
				.orElseThrow(() -> new ResourceNotFoundException("Tank", "tankId", tankId));
		if (!tankRepository.existsById(medewerkerDetails.getMedewerkerId())) {
			tank.addTankBeheerder(medewerkerDetails);
		}else {
			System.out.println("BENDE AAN DE SCHIJTERIJ DIKKE KANKER HOMO??");
		}
		return tankRepository.save(tank);

	}

}
