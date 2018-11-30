package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.exceptions.EntityExistsException;
import com.lorisensori.application.exceptions.ResourceNotFoundException;
import com.lorisensori.application.interfaces.BedrijfRepository;
import com.lorisensori.application.interfaces.MedewerkerRepository;
import com.lorisensori.application.interfaces.TankRepository;
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
    private final TankRepository tankRepository;
    private final MedewerkerRepository medewerkerRepository;

    @Autowired
    public BedrijfController(BedrijfRepository bedrijfRepository, TankRepository tankRepository, MedewerkerRepository medewerkerRepository) {
        this.bedrijfRepository = bedrijfRepository;
        this.tankRepository = tankRepository;
        this.medewerkerRepository = medewerkerRepository;
    }

    //Get all
    @GetMapping("/bedrijf/")
    public List<Bedrijf> getAllBedrijf() {
        return bedrijfRepository.findAll();
    }

    //Get one
    @GetMapping("/bedrijf/{bedrijfsnaam}")
    public Bedrijf getBedrijfByBedrijfsnaam(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam) {
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
        if (!medewerkerRepository.existsByBedrijf_Bedrijfsnaam(bedrijfsnaam)) {
            bedrijf.addMedewerker(medewerkerDetails);
            return bedrijfRepository.save(bedrijf);
        } else {
            throw new EntityExistsException("Medewerker", "Gebruikersnaam", medewerkerDetails.getGebruikersnaam());
        }
    }

    @PutMapping("/bedrijf/addtank/{bedrijfsnaam}")
    public Bedrijf addTank(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam, @Valid @RequestBody Tank tankDetails) {
        Bedrijf bedrijf = bedrijfRepository.findById(bedrijfsnaam)
                .orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfsnaam", bedrijfsnaam));
        if (!tankRepository.existsByBedrijfsnaam(bedrijfsnaam)) {
            bedrijf.addTank(tankDetails);
            return bedrijfRepository.save(bedrijf);
        } else {
            throw new EntityExistsException("Tank", "TankNaam", tankDetails.getTanknaam());
        }
    }

    //Add contactpersoon van bedrijf
    @PutMapping("/bedrijf/setcontactpersoon/{bedrijfsnaam}")
    public Bedrijf setContactpersoon(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam, @Valid @RequestBody Medewerker medewerkerDetails) {
        Bedrijf bedrijf = bedrijfRepository.findById(bedrijfsnaam)
                .orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfsnaam", bedrijfsnaam));
        if (bedrijf.getContactpersoon() == null) {
            bedrijf.setContactpersoon(medewerkerDetails);
            return bedrijfRepository.save(bedrijf);
        } else {
            throw new EntityExistsException("ContactPersoon", "Voornaam", medewerkerDetails.getVoornaam());
        }
    }

    //Update a Bedrijf
    @PutMapping("/bedrijf/{bedrijfsnaam}")
    public Bedrijf updateBedrijf(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam,
                                 @Valid @RequestBody Bedrijf bedrijfDetails) {
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
    public ResponseEntity<?> deleteBedrijf(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam) {
        Bedrijf bedrijf = bedrijfRepository.findById(bedrijfsnaam)
                .orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfsnaam", bedrijfsnaam));

        bedrijfRepository.delete(bedrijf);
        return ResponseEntity.ok().build();
    }

}
