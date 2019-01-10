package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.DTOs.tankDTOs.TankDTO;
import com.lorisensori.application.annotations.CurrentUser;
import com.lorisensori.application.domain.CustomUserDetails;
import com.lorisensori.application.domain.Sensorgegevens;
import com.lorisensori.application.domain.Tank;
import com.lorisensori.application.exceptions.EntityExistsException;
import com.lorisensori.application.service.TankServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class TankController {

	
    @Autowired
	private TankServiceImpl tankService;


    @Autowired
    private ModelMapper modelMapper;

    //Get all Tanks of current user from his Bedrijf
	//@PreAuthorize("hasRole('USER')")
    @GetMapping("/tank/")
    public Set<TankDTO> getAllTank(@CurrentUser CustomUserDetails currentUser) {
        Set<Tank> tanks = tankService.findByBedrijf(currentUser.getBedrijf());
        return tanks.stream().map(tank -> convertToDto(tank)).collect(Collectors.toSet());
        
    }

	
    //Get tank by tankId
	@PreAuthorize("hasRole('USER')")
    @GetMapping("/tank/{tankid}")
    public TankDTO getTankById(@PathVariable(value = "tankid") Long tankId) {
        Tank tank = tankService.findByTankId(tankId);
        return convertToDto(tank);
    }
	
	//Update tank
	//TODO: niet zeker of dit de juiste manier is.
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/tank/updateTank/")
	public TankDTO updateTank(@Valid @RequestBody TankDTO tankDTO) throws ParseException {
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
		return convertToDto(tank);
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
    
    private TankDTO convertToDto(Tank tank) {
    	TankDTO tankDTO = modelMapper.map(tank, TankDTO.class);
    	return tankDTO;
    }
    
    private Tank convertToEntity(TankDTO tankDTO) throws ParseException {
    	Tank tank = modelMapper.map(tankDTO, Tank.class);
    	return tank;
    }

}
