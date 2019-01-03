package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.DTOs.medewerkerDTOs.MedewerkerDTO;
import com.lorisensori.application.DTOs.medewerkerDTOs.UpdateMedewerkerDTO;
import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.service.MedewerkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PreAuthorize("hasRole('ADMIN')")
    public List<MedewerkerDTO> getAllMedewerkers() {
        return medewerkerService.findAll();
    }

    //Create a new Medewerker
    @PostMapping("/medewerker/create")
    @PreAuthorize("hasRole('ADMIN')")
    public void createMedewerker(Medewerker medewerker ) {
        medewerkerService.save(medewerker);
    }

    //Get a single Medewerker
    @GetMapping("/medewerker/{medewerkerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Medewerker getMedewerkerByid(@PathVariable(value = "medewerkerId") Long medewerkerId) {
        return medewerkerService.findById(medewerkerId);
    }

    //Update a Medewerker
    @PutMapping("/medewerker/update/{medewerkerId}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public UpdateMedewerkerDTO updateMedewerker(@PathVariable Long medewerkerId, @RequestBody UpdateMedewerkerDTO updateMedewerkerDTO){
        return medewerkerService.update(medewerkerId, updateMedewerkerDTO);
    }

    //Delete a Medewerker
    @DeleteMapping("/medewerker/delete/{medewerkerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteMedewerker(@PathVariable Long id){
        medewerkerService.delete(id);
    }

}
