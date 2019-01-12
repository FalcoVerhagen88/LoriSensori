package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.DTOs.medewerkerDTOs.MedewerkerDTO;
import com.lorisensori.application.annotations.CurrentUser;
import com.lorisensori.application.domain.CustomUserDetails;
import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.service.BedrijfService;
import com.lorisensori.application.service.MedewerkerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class MedewerkerController {


    private final MedewerkerService medewerkerService;

    private final ModelMapper modelMapper;

    private final BedrijfService bedrijfService;

    @Autowired
    public MedewerkerController(MedewerkerService medewerkerService, ModelMapper modelMapper, BedrijfService bedrijfService) {
        this.medewerkerService = medewerkerService;
        this.modelMapper = modelMapper;
        this.bedrijfService = bedrijfService;
    }

    //Krijg gegevens ingelogde gebruiker
	@PreAuthorize("hasRole('USER')")
    @GetMapping("/medewerker/huidigeGebruiker")
    public MedewerkerDTO getCurrentUser(@CurrentUser CustomUserDetails currentUser) {
		Medewerker medewerker = medewerkerService.findById(currentUser.getId());
		return convertToDTO(medewerker);		
    }

    //Get a single Medewerker
	@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/medewerker/{medewerkerId}")
    public MedewerkerDTO getMedewerkerByid(@PathVariable(value = "medewerkerId") Long medewerkerId) {
        Medewerker medewerker = medewerkerService.findById(medewerkerId);
        return convertToDTO(medewerker);
    }
	
	//Get all medewerkers from Bedrijf
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/medewerker/bedrijf")
	public Set<MedewerkerDTO> getAllMedewerkersFromBedrijf(@RequestParam(value = "bedrijfsnaam") String bedrijfsnaam) {
		Set<Medewerker> medewerkers = medewerkerService.findByBedrijf(bedrijfService.findByBedrijfsnaam(bedrijfsnaam));
		return medewerkers.stream().map(this::convertToDTO).collect(Collectors.toSet());
	}

    private MedewerkerDTO convertToDTO(Medewerker medewerker) {
    	modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    	modelMapper.getConfiguration().setAmbiguityIgnored(true);

        return modelMapper.map(medewerker, MedewerkerDTO.class);
    }
    
}
