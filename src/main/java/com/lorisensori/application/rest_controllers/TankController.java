package com.lorisensori.application.rest_controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lorisensori.application.DTOs.tankDTOs.SensorLogDTO;
import com.lorisensori.application.DTOs.tankDTOs.SensorgegevensDTO;
import com.lorisensori.application.DTOs.tankDTOs.SensorgegevensExtraDTO;
import com.lorisensori.application.DTOs.tankDTOs.TankBedrijfDTO;
import com.lorisensori.application.DTOs.tankDTOs.TankCreateDTO;
import com.lorisensori.application.DTOs.tankDTOs.TankDTO;
import com.lorisensori.application.TTN.DownlinkHandler;
import com.lorisensori.application.TTN.TtnClient;
import com.lorisensori.application.annotations.CurrentUser;
import com.lorisensori.application.domain.Bedrijf;
import com.lorisensori.application.domain.CustomUserDetails;
import com.lorisensori.application.domain.SensorLog;
import com.lorisensori.application.domain.Sensorgegevens;
import com.lorisensori.application.domain.Tank;
import com.lorisensori.application.exceptions.EntityExistsException;
import com.lorisensori.application.service.BedrijfService;
import com.lorisensori.application.service.SensorLogService;
import com.lorisensori.application.service.SensorgegevensService;
import com.lorisensori.application.service.TankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.thethingsnetwork.data.common.messages.DownlinkMessage;
import org.thethingsnetwork.data.mqtt.Client;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class TankController {

    private final TankService tankService;
    private final SensorgegevensService sensorgegevensService;
    private final BedrijfService bedrijfService;
    private final TtnClient client;
    private final SensorLogService sensorLogService;

    @Autowired
    public TankController(TankService tankService, SensorgegevensService sensorgegevensService, BedrijfService bedrijfService, TtnClient client, SensorLogService sensorLogService) {
        this.tankService = tankService;
        this.sensorgegevensService = sensorgegevensService;
        this.bedrijfService = bedrijfService;
        this.client = client;
        this.sensorLogService = sensorLogService;
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
//TODO Is de tanknaam uniek? of is de combinatie tanknaam en bedrijf uniek?
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
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/tank/sensorlog/{tank_id}")
    public Set<SensorLogDTO> getAllSensorLog(@PathVariable(value = "tank_id") Long tankId) {
    	Set<SensorLog> sensorLog = sensorLogService.findByTank(tankService.findByTankId(tankId));
    	return sensorLog.stream().map(sensorLogService::convertToDto).collect(Collectors.toSet());

    }

    /////////////////////////////////////////////////////////////////////////////
    
    //SensorGegevensenkel
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/tank/laatstesensorgegevens/{tank_id}")
    public SensorgegevensDTO getLaatsteSensorgegeven(@PathVariable(value = "tank_id") Long tankId) {
    	Set<Sensorgegevens> sensorgegevens = sensorgegevensService.findByTank(tankService.findByTankId(tankId));
    	SensorgegevensExtraDTO sensorgegevensExtraDTO = sensorgegevensService.convertToExtraDto(Collections.max(sensorgegevens));
    	sensorgegevensExtraDTO.setDevId(tankService.findByTankId(tankId).getDevId());
    	sensorgegevensExtraDTO.setOpeningstijd(tankService.findByTankId(tankId).getOpeningstijd());
    	sensorgegevensExtraDTO.setSluitingstijd(tankService.findByTankId(tankId).getSluitingstijd());
    	sensorgegevensExtraDTO.setTankId(tankId);
    	return sensorgegevensExtraDTO;
    }

    //////////////////////////////////////////////////////////////////////////////
    //SensorLog
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/tank/vanBedrijf/")
    public Set<TankBedrijfDTO> getTanksFromBedrijf(@CurrentUser CustomUserDetails currentUser) {
    	Set<Tank> tanks = tankService.findByBedrijf(currentUser.getBedrijf());
    	return tanks.stream().map(tankService::convertToTankBedrijfDTO).collect(Collectors.toSet());
    }
    
    //Tank toevoegen
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/tank/addTank/{bedrijfsnaam}")
    public TankDTO addTankToBedrijf(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam, @RequestBody TankCreateDTO tankCreateDTO) {
    	Bedrijf bedrijf = bedrijfService.findByBedrijfsnaam(bedrijfsnaam);
    	Tank tank = tankService.convertToEntityCreate(tankCreateDTO);
    	tankService.save(tank);
    	bedrijf.addTank(tank);
    	bedrijfService.save(bedrijf);
    	return tankService.convertToDto(tank);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/tank/removeTank/{tank_id}")
    public void removeTank(@PathVariable(value = "tank_id") Long tankId) {
    	Bedrijf bedrijf = tankService.findByTankId(tankId).getBedrijf();
    	bedrijf.removeTank(tankService.findByTankId(tankId));
    	bedrijfService.save(bedrijf);
    	
    }

    //DownlinkMessage
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/tank/downlink", consumes = "application/json")
    public void getDownlinkFromRequest(@RequestBody Map<String, Object> payload) throws JsonProcessingException, URISyntaxException {

        System.out.println(payload);
        DownlinkHandler downlinkHandler = new DownlinkHandler();
        String dev_id = downlinkHandler.getDevIdTank(payload);
        System.out.println(dev_id);
        Client ttnClient = client.getInstanceOfClient();
        try {

            ttnClient.start();
//            DownlinkMessage response = new DownlinkMessage(1, downlinkHandler.getDownlinkMessage(payload));

           // System.out.println("Sending: " + response);
            ttnClient.send(dev_id, downlinkHandler.getDownlinkMessage(payload));

        } catch (Exception ex) {
            System.out.println("Response failed: " + ex.getMessage());
        }
    }
}
