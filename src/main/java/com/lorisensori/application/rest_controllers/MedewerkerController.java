package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.DTO.MedewerkerDTO;
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
    public List<MedewerkerDTO> getAllMedewerkers(){
        return medewerkerRepository.findAll();
    }



    //Create a new Medewerker
    @PostMapping("/medewerker/")
    public MedewerkerDTO createMedewerker(@Valid @RequestBody MedewerkerDTO medewerker){
        return medewerkerRepository.save(medewerker);
    }
    //Get a single Medewerker
    @GetMapping("/medewerker/{gebruikersnaam}")
    public MedewerkerDTO getMedewerkerByid(@PathVariable(value = "gebruikersnaam") String gebruikersnaam){
        return medewerkerRepository.findById(gebruikersnaam)
                .orElseThrow(() -> new ResourceNotFoundException("Medewerker", "gebruikersnaam", gebruikersnaam ));
    }
    //Update a Medewerker
    @PutMapping("/medewerker/{gebruikersnaam}")
    public MedewerkerDTO updateMedewerker(@PathVariable(value = "gebruikersnaam") String gebruikersnaam,
                                       @Valid @RequestBody MedewerkerDTO medewerkerDetails){
        MedewerkerDTO medewerker = medewerkerRepository.findById(gebruikersnaam)
                .orElseThrow(() -> new ResourceNotFoundException("Medewerker", "gebruikersnaam", gebruikersnaam));
        medewerker.setVoornaam(medewerkerDetails.getVoornaam());
        medewerker.setAchternaam(medewerkerDetails.getAchternaam());

        MedewerkerDTO updatedMedewerker = medewerkerRepository.save(medewerker);
        return updatedMedewerker;
    }
    //Delete a Medewerker
    @DeleteMapping("/medewerker/{gebruikersnaam}")
    public ResponseEntity<?> deleteMedewerker(@PathVariable(value = "gebruikersnaam") String gebruikersnaam){
        MedewerkerDTO medewerker = medewerkerRepository.findById(gebruikersnaam)
                .orElseThrow(() -> new ResourceNotFoundException("Medewerker", "gebruikersnaam", gebruikersnaam));

        medewerkerRepository.delete(medewerker);
        return ResponseEntity.ok().build();
    }
}
