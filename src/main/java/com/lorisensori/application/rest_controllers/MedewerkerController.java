package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.exceptions.EntityExistsException;
import com.lorisensori.application.exceptions.ResourceNotFoundException;
import com.lorisensori.application.interfaces.BedrijfRepository;
import com.lorisensori.application.interfaces.MedewerkerRepository;
import com.lorisensori.application.logic.Medewerker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MedewerkerController {

    private final MedewerkerRepository medewerkerRepository;
    private final BedrijfRepository bedrijfRepository;

    @Autowired
    public MedewerkerController(MedewerkerRepository medewerkerRepository, BedrijfRepository bedrijfRepository) {
        this.medewerkerRepository = medewerkerRepository;
        this.bedrijfRepository = bedrijfRepository;
    }

    //Get all Medewerkers
    @GetMapping("/medewerker/")
    public List<Medewerker> getAllMedewerkers() {
        return medewerkerRepository.findAll();
    }


    //Create a new Medewerker
    @PostMapping("/medewerker/")
    public Medewerker createMedewerker(@Valid @RequestBody Medewerker medewerker) {
        if (!medewerkerRepository.existsByVoornaam(medewerker.getVoornaam())){
            return medewerkerRepository.save(medewerker);
        }else {
            throw new EntityExistsException("Medewerker", "Voornaam", medewerker.getVoornaam());
        }
    }

    //Get a single Medewerker
    @GetMapping("/medewerker/{gebruikersnaam}")
    public Medewerker getMedewerkerByid(@PathVariable(value = "medewerkerId") Long medewerkerId) {
        return medewerkerRepository.findById(medewerkerId)
                .orElseThrow(() -> new ResourceNotFoundException("Medewerker", "MedewerkerId", medewerkerId));
    }

    //Update a Medewerker
    @PutMapping("/medewerker/{gebruikersnaam}")
    public Medewerker updateMedewerker(@PathVariable(value = "medewerkerId") Long medewerkerId,
                                       @Valid @RequestBody Medewerker medewerkerDetails) {
        Medewerker medewerker = medewerkerRepository.findById(medewerkerId)
                .orElseThrow(() -> new ResourceNotFoundException("Medewerker", "gebruikersnaam", medewerkerDetails.getMedewerkerId()));
        medewerker.setVoornaam(medewerkerDetails.getVoornaam());
        medewerker.setAchternaam(medewerkerDetails.getAchternaam());

        Medewerker updatedMedewerker = medewerkerRepository.save(medewerker);
        return updatedMedewerker;
    }

    //Delete a Medewerker
    @DeleteMapping("/medewerker/{gebruikersnaam}")
    public ResponseEntity<?> deleteMedewerker(@PathVariable(value = "medewerkerId") Long medewerkerId) {
        Medewerker medewerker = medewerkerRepository.findById(medewerkerId)
                .orElseThrow(() -> new ResourceNotFoundException("Medewerker", "gebruikersnaam", medewerkerId));

        medewerkerRepository.delete(medewerker);
        return ResponseEntity.ok().build();
    }
}
