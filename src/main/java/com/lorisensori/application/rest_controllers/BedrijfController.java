package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.exceptions.ResourceNotFoundException;
import com.lorisensori.application.interfaces.BedrijfRepository;
import com.lorisensori.application.logic.Bedrijf;
import com.lorisensori.application.logic.Medewerker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BedrijfController {
	
	private final BedrijfRepository bedrijfRepository;
	
	@Autowired
	public BedrijfController(BedrijfRepository bedrijfRepository) {
		this.bedrijfRepository = bedrijfRepository;
	}
	
	//Get all
	@GetMapping("/bedrijf/")
	public List<Bedrijf> getAllBedrijf(){
		return bedrijfRepository.findAll();
	}
	
	//Get one
	@GetMapping("/bedrijf/{bedrijfsnaam}")
	public Bedrijf getBedrijfByBedrijfsnaam(@PathVariable(value= "bedrijfsnaam") String bedrijfsnaam) {
		return bedrijfRepository.findById(bedrijfsnaam)
				.orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfsnaam", bedrijfsnaam));
	}
	
	//Create new Bedrijf
	@PostMapping("/bedrijf/")
	public Bedrijf createBedrijf(@Valid @RequestBody Bedrijf bedrijf) {
		return bedrijfRepository.save(bedrijf);
	}
	
	//Add medewerker bedrijf
	@PutMapping("/bedrijf/addmedewerker/{bedrijfsnaam}")
	public Bedrijf addMedewerker(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam, @Valid @RequestBody Medewerker medewerkerDetails) {
		Bedrijf bedrijf = bedrijfRepository.findById(bedrijfsnaam) 
				.orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfsnaam", bedrijfsnaam));
		bedrijf.addMedewerker(medewerkerDetails);
		
		Bedrijf updatedBedrijf = bedrijfRepository.save(bedrijf);
		return updatedBedrijf;
	}
	
	//Add contactpersoon van bedrijf
	@PutMapping("/bedrijf/setcontactpersoon/{bedrijfsnaam}")
	public Bedrijf setContactpersoon(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam, @Valid @RequestBody Medewerker medewerkerDetails) {
		Bedrijf bedrijf = bedrijfRepository.findById(bedrijfsnaam) 
				.orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfsnaam", bedrijfsnaam));
		bedrijf.setContactpersoon(medewerkerDetails);
		
		Bedrijf updatedBedrijf = bedrijfRepository.save(bedrijf);
		return updatedBedrijf;
	}

	//Update a Bedrijf
	@PutMapping("/bedrijf/{bedrijfsnaam}")
	public Bedrijf updateBedrijf(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam,
								@Valid @RequestBody Bedrijf bedrijfDetails){
		Bedrijf bedrijf = bedrijfRepository.findById(bedrijfsnaam)
				.orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfsnaam", bedrijfsnaam));
		bedrijf.setBtwNummer(bedrijfDetails.getBtwNummer());
		bedrijf.setAdres(bedrijfDetails.getAdres());
		bedrijf.setContactpersoon(bedrijfDetails.getContactpersoon());
		bedrijf.setKvkNummer(bedrijfDetails.getKvkNummer());
		bedrijf.setRekeningnummer(bedrijfDetails.getRekeningnummer());
		bedrijf.setTelefoonnummer(bedrijfDetails.getTelefoonnummer());
		bedrijf.setVatNummer(bedrijfDetails.getVatNummer());
		bedrijf.setStatus(bedrijfDetails.getStatus());
		
		Bedrijf updatedBedrijf = bedrijfRepository.save(bedrijf);
		return updatedBedrijf;
	}
	
	//Delete a Bedrijf
	@DeleteMapping("/bedrijf/{bedrijfsnaam}")
	public ResponseEntity<?> deleteBedrijf(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam){
		Bedrijf bedrijf = bedrijfRepository.findById(bedrijfsnaam)
				.orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfsnaam", bedrijfsnaam));
		
		bedrijfRepository.delete(bedrijf);
		return ResponseEntity.ok().build();
	}
	
}
