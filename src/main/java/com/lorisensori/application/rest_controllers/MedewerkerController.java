package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.exceptions.EntityExistsException;
import com.lorisensori.application.service.MedewerkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MedewerkerController {


    private final MedewerkerService medewerkerService;

    @Autowired
    public MedewerkerController(MedewerkerService medewerkerService) {
        this.medewerkerService = medewerkerService;
    }

    //Get all Medewerkers
    @GetMapping("/medewerker/")
    public Iterable<Medewerker> getAllMedewerkers() {
        return medewerkerService.findAll();
    }


    //Create a new Medewerker
    @PostMapping("/medewerker/")
    public Medewerker createMedewerker(@Valid @RequestBody Medewerker medewerker) {
        if (!medewerkerService.existsByVoornaam(medewerker.getVoornaam())) {
            return medewerkerService.save(medewerker);
        } else {
            throw new EntityExistsException("Medewerker", "Voornaam", medewerker.getVoornaam());
        }
    }

    //Get a single Medewerker
    @GetMapping("/medewerker/{gebruikersnaam}")
    public Optional<Medewerker> getMedewerkerByid(@PathVariable(value = "medewerkerId") Long medewerkerId) {
        return medewerkerService.findById(medewerkerId);
    }

    //Update a Medewerker


    //Delete a Medewerker

}
