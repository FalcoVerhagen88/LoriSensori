package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.DTOs.tankDTOs.TankDTO;
import com.lorisensori.application.annotations.CurrentUser;
import com.lorisensori.application.domain.CustomUserDetails;
import com.lorisensori.application.domain.Tank;
import com.lorisensori.application.exceptions.EntityExistsException;
import com.lorisensori.application.service.TankService;
import com.lorisensori.application.service.TankServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class TankController {


    private final TankService tankService;
    private final ModelMapper modelMapper;

    @Autowired
    public TankController(TankServiceImpl tankService, ModelMapper modelMapper) {
        this.tankService = tankService;
        this.modelMapper = modelMapper;
    }

    //Get all Tanks of current user from his Bedrijf
	@PreAuthorize("hasRole('USER')")
    @GetMapping("/tank/")
    public Set<TankDTO> getAllTank(@CurrentUser CustomUserDetails currentUser) {
        Set<Tank> tanks = tankService.findByBedrijf(currentUser.getBedrijf());
        return tanks.stream().map(tankService::convertToDto).collect(Collectors.toSet());

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/allTanks")
    public List<TankDTO> getAllTanks(){
        List<Tank> tanks = tankService.findAll();
        return tanks.stream().map(tankService::convertToDto).collect(Collectors.toList());
    }


    //Get tank by dev_Id
	@PreAuthorize("hasRole('USER')")
    @GetMapping("/tank/{dev_id}")
    public TankDTO getTankByDevId(@PathVariable(value = "dev_id") String devId) {
        Tank tank = tankService.findByDevId(devId);
        return tankService.convertToDto(tank);
    }

	//Update tank
	//TODO: niet zeker of dit de juiste manier is.
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/tank/updateTank")
	public TankDTO updateTank(@Valid @RequestBody TankDTO tankDTO) {

        return tankService.updateTank(tankDTO);
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

}
