package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.DTOs.tankDTOs.TankDTO;
import com.lorisensori.application.domain.Tank;
import com.lorisensori.application.exceptions.EntityExistsException;
import com.lorisensori.application.service.TankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TankController {

    private final TankService tankService;

    @Autowired
    public TankController(TankService tankService) {
        this.tankService = tankService;
    }

    //Get all Tanks
    @GetMapping("/tank/")
    public List<TankDTO> getAllTank() {
        return tankService.findAll();
    }

    //Get one tank
    @GetMapping("/tank/{tankid}")
    public Tank getTankById(@PathVariable(value = "tankid") Long tankId) {
        return tankService.findByTankId(tankId);
    }

    //Create a new Tank
    @PostMapping("/tank/")
    public Tank createTank(@Valid @RequestBody Tank tank) {

        if (!tankService.existsByTanknaam(tank.getTanknaam())) {

            return tankService.save(tank);
        } else {
            throw new EntityExistsException("Tank", "Tanknaam", tank.getTanknaam());
        }
    }

}
