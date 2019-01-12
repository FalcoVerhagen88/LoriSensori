package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.DTOs.tankDTOs.SensorgegevensDTO;
import com.lorisensori.application.DTOs.tankDTOs.TankDTO;
import com.lorisensori.application.annotations.CurrentUser;
import com.lorisensori.application.domain.CustomUserDetails;
import com.lorisensori.application.domain.SensorLog;
import com.lorisensori.application.domain.Sensorgegevens;
import com.lorisensori.application.domain.Tank;
import com.lorisensori.application.exceptions.EntityExistsException;
import com.lorisensori.application.service.SensorgegevensService;
import com.lorisensori.application.service.TankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class TankController {

    private final TankService tankService;
    private final SensorgegevensService sensorgegevensService;

    @Autowired
    public TankController(TankService tankService, SensorgegevensService sensorgegevensService) {
        this.tankService = tankService;
        this.sensorgegevensService = sensorgegevensService;
    }

    //Get all Tanks of current user from his Bedrijf
    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/tank/")
    public Set<TankDTO> getAllTank(@CurrentUser CustomUserDetails currentUser) {
        Set<Tank> tanks = tankService.findByBedrijf(currentUser.getBedrijf());
        return tanks.stream().map(tankService::convertToDto).collect(Collectors.toSet());
    }

    //Get tank by tankId
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/tank/{tankid}")
    public TankDTO getTankById(@PathVariable(value = "tankid") Long tankId) {
        Tank tank = tankService.findByTankId(tankId);
        return tankService.convertToDto(tank);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/tank")
    public TankDTO getTankByDevId(@RequestParam(value = "dev_id") String devId){
        Tank tank = tankService.findByDevId(devId);
        return tankService.convertToDto(tank);
    }

    //Update tank
    //TODO: niet zeker of dit de juiste manier is.
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/tank/updateTank/")
    public TankDTO updateTank(@Valid @RequestBody TankDTO tankDTO){
        Tank tank = tankService.findByTankId(tankDTO.getTankId());
        tank.setBouwjaar(tankDTO.getBouwjaar());
        tank.setDiameter(tankDTO.getDiameter());
        tank.setGewicht(tankDTO.getGewicht());
        tank.setInhoudLiters(tankDTO.getInhoudLiters());
        tank.setLengte(tankDTO.getLengte());
        tank.setMeldingTanken(tankDTO.getMeldingTanken());
        tank.setOpeningstijd(tankDTO.getOpeningstijd());
        tank.setSluitingstijd(tankDTO.getSluitingstijd());
        tank.setTanknummer(tankDTO.getTanknummer());
        tank.setType(tankDTO.getType());
        tank.setStatus(tankDTO.getStatus());
        tank.setTanknaam(tankDTO.getTanknaam());
        tankService.save(tank);
        return tankService.convertToDto(tank);
    }

    //Create a new Tank
    @PostMapping("/tank/create")
    @PreAuthorize("hasRole('ADMIN')")

    public Tank createTank(@Valid @RequestBody Tank tank) {

        if (!tankService.existsByTanknaam(tank.getTanknaam())) {

            return tankService.save(tank);
        } else {
            throw new EntityExistsException("Tank", "Tanknaam", tank.getTanknaam());
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //SensorGegevens
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/tank/sensorgegevens/{tank_id}")
    public Set<SensorgegevensDTO> getAllSensorgegeven(@PathVariable(value = "tank_id") Long tankId) {
        Set<Sensorgegevens> sensorgegevens = sensorgegevensService.findByTank(tankService.findByTankId(tankId));
        return sensorgegevens.stream().map(sensorgegevensService::convertToDto).collect(Collectors.toSet());
    }

    //////////////////////////////////////////////////////////////////////////////
    //SensorLog


}
