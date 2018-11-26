package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.DTO.MedewerkerDTO;
import com.lorisensori.application.logic.Medewerker;
import com.lorisensori.application.exceptions.ResourceNotFoundException;
import com.lorisensori.application.interfaces.MedewerkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController//this annotation is a combination of Spring’s @Controller and @ResponseBody annotations. The @Controller annotation is used to define a controller and the @ResponseBody annotation is used to indicate that the return value of a method should be used as the response body of the request.
@RequestMapping("/api")//this annotation  declares that the url for all the apis in this controller will start with /api.
public class MedewerkerController {

    private final MedewerkerRepository medewerkerRepository;

    @Autowired
    public MedewerkerController(MedewerkerRepository medewerkerRepository) {
        this.medewerkerRepository = medewerkerRepository;
    }

    //Get all Medewerkers
    @GetMapping("/medewerker/")
    public List<Medewerker> getAllMedewerkers(){
        return medewerkerRepository.findAll();
    }

    //Create a new Medewerker
    @PostMapping("/medewerker/")
    public Medewerker createMedewerker(@Valid @RequestBody Medewerker medewerker){
        return medewerkerRepository.save(medewerker);
    }
    //Get a single Medewerker
    @GetMapping("/medewerker/{medewerkerId}")
    public Medewerker getMedewerkerByid(@PathVariable(value = "medewerkerId") Long medewerkerId){
        return medewerkerRepository.findById(medewerkerId)
                .orElseThrow(() -> new ResourceNotFoundException("Medewerker", "gebruikersnaam", medewerkerId ));
    }
    //Update a Medewerker
    @PutMapping("/medewerker/{medewerkerId}")
    public Medewerker updateMedewerker(@PathVariable(value = "medewerkerId") Long medewerkerId,
                                       @Valid @RequestBody Medewerker medewerkerDetails){
        Medewerker medewerker = medewerkerRepository.findById(medewerkerId)
                .orElseThrow(() -> new ResourceNotFoundException("Medewerker", "gebruikersnaam", medewerkerId));
        medewerker.setVoornaam(medewerkerDetails.getVoornaam());
        medewerker.setAchternaam(medewerkerDetails.getAchternaam());

        Medewerker updatedMedewerker = medewerkerRepository.save(medewerker);
        return updatedMedewerker;
    }
    //Delete a Medewerker
    @DeleteMapping("/medewerker/{medewerkerId}")
    public ResponseEntity<?> deleteMedewerker(@PathVariable(value = "medewerkerId") Long medewerkerId){
        Medewerker medewerker = medewerkerRepository.findById(medewerkerId)
                .orElseThrow(() -> new ResourceNotFoundException("Medewerker", "gebruikersnaam", medewerkerId));

        medewerkerRepository.delete(medewerker);
        return ResponseEntity.ok().build();
    }
}
