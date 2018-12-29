package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.service.MedewerkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
//        return medewerkerService.findAll();
        return null;
    }


    //Create a new Medewerker
    @PostMapping("/medewerker/create")
    public Medewerker createMedewerker(@Valid @RequestBody Medewerker medewerker) {
        return medewerkerService.save(medewerker);
    }

    //Get a single Medewerker
    @GetMapping("/medewerker/{medewerkerId}")
    public Medewerker getMedewerkerByid(@PathVariable(value = "medewerkerId") Long medewerkerId) {
        return medewerkerService.findById(medewerkerId);
    }

    //Update a Medewerker


    //Delete a Medewerker

}
