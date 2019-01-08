package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.DTOs.medewerkerDTOs.MedewerkerDTO;
import com.lorisensori.application.annotations.CurrentUser;
import com.lorisensori.application.domain.CustomUserDetails;
import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.service.BedrijfService;
import com.lorisensori.application.service.MedewerkerService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class MedewerkerController {


    private final MedewerkerService medewerkerService;

    @Autowired
    public MedewerkerController(MedewerkerService medewerkerService) {
        this.medewerkerService = medewerkerService;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BedrijfService bedrijfService;

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
	@GetMapping("/medewerker/{bedrijfsnaam}")
	public Set<MedewerkerDTO> getAllMedewerkersFromBedrijf(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam) {
		Set<Medewerker> medewerkers = medewerkerService.findByBedrijf(bedrijfService.findByBedrijfsnaam(bedrijfsnaam));
		return medewerkers.stream().map(medewerker -> convertToDTO(medewerker)).collect(Collectors.toSet());
	}

    //Create a new Medewerker
    @PostMapping("/medewerker/create")
    public Medewerker createMedewerker(@Valid @RequestBody Medewerker medewerker) {
        return medewerkerService.save(medewerker);
    }
*/
	//Update a Medewerker


    //Delete a Medewerker

    private MedewerkerDTO convertToDTO(Medewerker medewerker) {
    	MedewerkerDTO medewerkerDTO = modelMapper.map(medewerker, MedewerkerDTO.class);
    	return medewerkerDTO;
    }

}
