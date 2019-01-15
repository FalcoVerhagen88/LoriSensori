package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.DTOs.medewerkerDTOs.MedewerkerDTO;
import com.lorisensori.application.annotations.CurrentUser;
import com.lorisensori.application.domain.CustomUserDetails;
import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.service.BedrijfService;
import com.lorisensori.application.service.MedewerkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MedewerkerController {


    private final MedewerkerService medewerkerService;
    private final BedrijfService bedrijfService;

    @Autowired
    public MedewerkerController(MedewerkerService medewerkerService, BedrijfService bedrijfService) {
        this.medewerkerService = medewerkerService;
        this.bedrijfService = bedrijfService;
    }

    //Krijg gegevens ingelogde gebruiker
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/medewerker/huidigeGebruiker")
    public MedewerkerDTO getCurrentUser(@CurrentUser CustomUserDetails currentUser) {
        Medewerker medewerker = medewerkerService.findById(currentUser.getId());
        return medewerkerService.convertToDto(medewerker);
    }

    //Get a single Medewerker
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/medewerker/{medewerkerId}")
    public MedewerkerDTO getMedewerkerByid(@PathVariable(value = "medewerkerId") Long medewerkerId) {
        Medewerker medewerker = medewerkerService.findById(medewerkerId);
        return medewerkerService.convertToDto(medewerker);
    }

    //Get all medewerkers from Bedrijf
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/medewerker/{bedrijfsnaam}")
    public Set<MedewerkerDTO> getAllMedewerkersFromBedrijf(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam) {
        Set<Medewerker> medewerkers = medewerkerService.findByBedrijf(bedrijfService.findByBedrijfsnaam(bedrijfsnaam));
        return medewerkers.stream().map(medewerkerService::convertToDto).collect(Collectors.toSet());
    }
}
