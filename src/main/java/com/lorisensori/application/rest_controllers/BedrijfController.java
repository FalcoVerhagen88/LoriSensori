package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.DTO.BedrijfDTO;
import com.lorisensori.application.DTO.MedewerkerDTO;
import com.lorisensori.application.DTO.TankDTO;
import com.lorisensori.application.exceptions.ResourceNotFoundException;
import com.lorisensori.application.interfaces.BedrijfRepository;

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
    public List<BedrijfDTO> getAllBedrijf(){
        return bedrijfRepository.findAll();
    }

    //Get one
    @GetMapping("/bedrijf/{bedrijfsnaam}")
    public BedrijfDTO getBedrijfByBedrijfsnaam(@PathVariable(value= "bedrijfsnaam") String bedrijfsnaam) {
        return bedrijfRepository.findById(bedrijfsnaam)
                .orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfsnaam", bedrijfsnaam));
    }

    //Create new Bedrijf
    @PostMapping("/bedrijf/")
    public BedrijfDTO createBedrijf(@Valid @RequestBody BedrijfDTO bedrijf) {
        return bedrijfRepository.save(bedrijf);
    }

    //Add medewerker bedrijf
    @PutMapping("/bedrijf/addmedewerker/{bedrijfsnaam}")
    public BedrijfDTO addMedewerker(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam, @Valid @RequestBody MedewerkerDTO medewerkerDetails) {
        BedrijfDTO bedrijf = bedrijfRepository.findById(bedrijfsnaam)
                .orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfsnaam", bedrijfsnaam));
        bedrijf.addMedewerker(medewerkerDetails);

        BedrijfDTO updatedBedrijf = bedrijfRepository.save(bedrijf);
        return updatedBedrijf;
    }

    //Add contactpersoon van bedrijf
    @PutMapping("/bedrijf/setcontactpersoon/{bedrijfsnaam}")
    public BedrijfDTO setContactpersoon(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam, @Valid @RequestBody MedewerkerDTO medewerkerDetails) {
        BedrijfDTO bedrijf = bedrijfRepository.findById(bedrijfsnaam)
                .orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfsnaam", bedrijfsnaam));
        bedrijf.setContactpersoon(medewerkerDetails);

        BedrijfDTO updatedBedrijf = bedrijfRepository.save(bedrijf);
        return updatedBedrijf;
    }

    //Add tank to bedrijf
    @PutMapping("/bedrijf/addtank/{bedrijfsnaam}")
    public BedrijfDTO addTank(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam, @Valid @RequestBody TankDTO tankDetails) {
        BedrijfDTO bedrijf = bedrijfRepository.findById(bedrijfsnaam)
                .orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfsnaam", bedrijfsnaam));
        bedrijf.addTank(tankDetails);

        BedrijfDTO updatedBedrijf = bedrijfRepository.save(bedrijf);
        return updatedBedrijf;
    }
    //Update a Bedrijf
    @PutMapping("/bedrijf/{bedrijfsnaam}")
    public BedrijfDTO updateBedrijf(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam,
                                            @Valid @RequestBody BedrijfDTO bedrijfDetails){
        BedrijfDTO bedrijf = bedrijfRepository.findById(bedrijfsnaam)
                .orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfsnaam", bedrijfsnaam));
        bedrijf.setBtwNummer(bedrijfDetails.getBtwNummer());
        bedrijf.setAdres(bedrijfDetails.getAdres());
        bedrijf.setContactpersoon(bedrijfDetails.getContactpersoon());
        bedrijf.setKvkNummer(bedrijfDetails.getKvkNummer());
        bedrijf.setRekeningnummer(bedrijfDetails.getRekeningnummer());
        bedrijf.setTelefoonnummer(bedrijfDetails.getTelefoonnummer());
        bedrijf.setVatNummer(bedrijfDetails.getVatNummer());
        bedrijf.setStatus(bedrijfDetails.getStatus());

        BedrijfDTO updatedBedrijf = bedrijfRepository.save(bedrijf);
        return updatedBedrijf;
    }

    //Delete a Bedrijf
    @DeleteMapping("/bedrijf/{bedrijfsnaam}")
    public ResponseEntity<?> deleteBedrijf(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam){
        BedrijfDTO bedrijf = bedrijfRepository.findById(bedrijfsnaam)
                .orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfsnaam", bedrijfsnaam));

        bedrijfRepository.delete(bedrijf);
        return ResponseEntity.ok().build();
    }

}
