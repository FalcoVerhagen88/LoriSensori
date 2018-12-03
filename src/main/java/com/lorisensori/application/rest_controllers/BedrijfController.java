package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.exceptions.EntityExistsException;
import com.lorisensori.application.exceptions.ResourceNotFoundException;
import com.lorisensori.application.interfaces.BedrijfRepository;
import com.lorisensori.application.interfaces.MedewerkerRepository;
import com.lorisensori.application.interfaces.TankRepository;
import com.lorisensori.application.domain.Bedrijf;
import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.domain.Tank;
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

        if (!bedrijfRepository.existsByBedrijfsnaam(bedrijf.getBedrijfsnaam())) {

            return bedrijfRepository.save(bedrijf);
        } else {
            throw new EntityExistsException("Bedrijf", "Bedrijfsnaam", bedrijf.getBedrijfsnaam());
        }
    }

    //Add medewerker bedrijf
    @PutMapping("/bedrijf/addmedewerker/{bedrijfsnaam}")
    public Bedrijf addMedewerker(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam, @Valid @RequestBody Medewerker medewerkerDetails) {
        Bedrijf bedrijf = bedrijfRepository.findById(bedrijfsnaam)
                .orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfsnaam", bedrijfsnaam));
        if (medewerkerRepository.existsByVoornaam(medewerkerDetails.getVoornaam())) {
            Medewerker existingMedewerker = medewerkerRepository.findByVoornaam(medewerkerDetails.getVoornaam())
                    .orElseThrow(() -> new ResourceNotFoundException("Medewerker", "Voornaam", medewerkerDetails.getVoornaam()));
            bedrijf.addMedewerker(existingMedewerker);
            existingMedewerker.setBedrijf(bedrijf);
            return bedrijfRepository.save(bedrijf);
        } else {
            bedrijf.addMedewerker(medewerkerDetails);
            medewerkerDetails.setBedrijf(bedrijf);
            return bedrijfRepository.save(bedrijf);
        }
    }

    @PutMapping("/bedrijf/addtank/{bedrijfsnaam}")
    public Bedrijf addTank(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam, @Valid @RequestBody Tank tankDetails) {
        Bedrijf bedrijf = bedrijfRepository.findById(bedrijfsnaam)
                .orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfsnaam", bedrijfsnaam));
        if (tankRepository.existsByTanknaam(tankDetails.getTanknaam())) {
            Tank existingTank = tankRepository.findByTanknaam(tankDetails.getTanknaam())
                    .orElseThrow(() -> new ResourceNotFoundException("Tank", "TankNaam", tankDetails.getTanknaam()));
            bedrijf.addTank(existingTank);
            existingTank.setBedrijf(bedrijf);
            return bedrijfRepository.save(bedrijf);
        } else {
            bedrijf.addTank(tankDetails);
            tankDetails.setBedrijf(bedrijf);
            return bedrijfRepository.save(bedrijf);
        }
    }

    //Add contactpersoon van bedrijf
    @PutMapping("/bedrijf/setcontactpersoon/{bedrijfsnaam}")
    public Bedrijf setContactpersoon(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam, @Valid @RequestBody Medewerker medewerkerDetails) {
        Bedrijf bedrijf = bedrijfRepository.findById(bedrijfsnaam)
                .orElseThrow(() -> new ResourceNotFoundException("Bedrijf", "bedrijfsnaam", bedrijfsnaam));
        if (medewerkerRepository.existsByVoornaam(medewerkerDetails.getVoornaam())) {
            Medewerker existingMedewerker = medewerkerRepository.findByVoornaam(medewerkerDetails.getVoornaam())
                    .orElseThrow(() -> new ResourceNotFoundException("Contactpersoon", "Voornaam", medewerkerDetails.getVoornaam()));
            bedrijf.setContactpersoon(existingMedewerker);
            return bedrijfRepository.save(bedrijf);
        } else {
            bedrijf.setContactpersoon(medewerkerDetails);
            return bedrijfRepository.save(bedrijf);
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

        return bedrijfRepository.save(bedrijf);
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
