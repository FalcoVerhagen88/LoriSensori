package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.exceptions.EntityExistsException;
import com.lorisensori.application.exceptions.ResourceNotFoundException;
import com.lorisensori.application.interfaces.BedrijfRepository;
import com.lorisensori.application.logic.Bedrijf;
import com.lorisensori.application.logic.Medewerker;
import com.lorisensori.application.logic.Tank;
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
    @GetMapping("/bedrijf/{bedrijfId}")
    public Bedrijf getBedrijfByBedrijfId(@PathVariable(value= "bedrijfId") Long bedrijfId) {
        return bedrijfRepository.findById(bedrijfId)
                .orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfId", bedrijfId));
    }

    //Create new Bedrijf
    @PostMapping("/bedrijf/")
    public Bedrijf createBedrijf(@Valid @RequestBody Bedrijf bedrijf) {
        return bedrijfRepository.save(bedrijf);
    }

    //Add medewerker bedrijf
    @PutMapping("/bedrijf/addmedewerker/{bedrijfId}")
    public Bedrijf addMedewerker(@PathVariable(value = "bedrijfId") Long bedrijfId, @Valid @RequestBody Medewerker medewerkerDetails) {
        Bedrijf bedrijf = bedrijfRepository.findById(bedrijfId)
                .orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfId", bedrijfId));
        if (!bedrijfRepository.existsById(medewerkerDetails.getMedewerkerId())) {
            bedrijf.addMedewerker(medewerkerDetails);
        }else {
            throw new EntityExistsException("Medewerker", "MedewerkerId", medewerkerDetails.getMedewerkerId());
        }
        return bedrijfRepository.save(bedrijf);
    }

    //Add contactpersoon van bedrijf
    @PutMapping("/bedrijf/setcontactpersoon/{bedrijfId}")
    public Bedrijf setContactpersoon(@PathVariable(value = "bedrijfId") Long bedrijfId, @Valid @RequestBody Medewerker medewerkerDetails) {
        Bedrijf bedrijf = bedrijfRepository.findById(bedrijfId)
                .orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfId", bedrijfId));
        bedrijf.setContactpersoon(medewerkerDetails);

        return bedrijfRepository.save(bedrijf);
    }

    //Add tank to bedrijf
    @PutMapping("/bedrijf/addtank/{bedrijfId}")
    public Bedrijf addTank(@PathVariable(value = "bedrijfId") Long bedrijfId, @Valid @RequestBody Tank tankDetails) {
        Bedrijf bedrijf = bedrijfRepository.findById(bedrijfId)
                .orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfId", bedrijfId));
        bedrijf.addTank(tankDetails);

        return bedrijfRepository.save(bedrijf);
    }
    //Update a Bedrijf
    @PutMapping("/bedrijf/{bedrijfId}")
    public Bedrijf updateBedrijf(@PathVariable(value = "bedrijfId") Long bedrijfId,
                                 @Valid @RequestBody Bedrijf bedrijfDetails){
        Bedrijf bedrijf = bedrijfRepository.findById(bedrijfId)
                .orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfId", bedrijfId));
        bedrijf.setBtwNummer(bedrijfDetails.getBtwNummer());
        bedrijf.setContactpersoon(bedrijfDetails.getContactpersoon());
        bedrijf.setKvkNummer(bedrijfDetails.getKvkNummer());
        bedrijf.setRekeningnummer(bedrijfDetails.getRekeningnummer());
        bedrijf.setTelefoonnummer(bedrijfDetails.getTelefoonnummer());
        bedrijf.setVatNummer(bedrijfDetails.getVatNummer());

        return bedrijfRepository.save(bedrijf);
    }

    //Delete a Bedrijf
    @DeleteMapping("/bedrijf/{bedrijfId}")
    public ResponseEntity<?> deleteBedrijf(@PathVariable(value = "bedrijfId") Long bedrijfId){
        Bedrijf bedrijf = bedrijfRepository.findById(bedrijfId)
                .orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfId", bedrijfId));

        bedrijfRepository.delete(bedrijf);
        return ResponseEntity.ok().build();
    }

}
