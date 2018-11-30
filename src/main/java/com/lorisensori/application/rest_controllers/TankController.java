package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.exceptions.ResourceNotFoundException;
import com.lorisensori.application.interfaces.TankRepository;
import com.lorisensori.application.logic.Tank;
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
    public List<Tank> getAllTank() {
        return tankRepository.findAll();
    }
	
/*	
	//Get all Tank from Bedrijf
	@GetMapping("/tank/{bedrijfsnaam}")
	public List<Tank> getAllTankBedrijf(){
		return tankRepository.findAll();
	}
*/

    //Get one tank
    @GetMapping("/tank/{tankid}")
    public Tank getTankById(@PathVariable(value = "tankid") Long tankId) {
        return tankRepository.findById(tankId)
                .orElseThrow(() -> new ResourceNotFoundException("Tank", "tankId", tankId));
    }

    //Create a new Tank
    @PostMapping("/tank/")
    public Tank createTank(@Valid @RequestBody Tank tank) {
        return tankRepository.save(tank);
    }

}
