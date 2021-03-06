package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.domain.Bedrijf;
import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.domain.Tank;
import com.lorisensori.application.exceptions.EntityExistsException;
import com.lorisensori.application.service.BedrijfService;
import com.lorisensori.application.service.MedewerkerService;
import com.lorisensori.application.service.TankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class BedrijfController {

    private final BedrijfService bedrijfService;
    private final TankService tankService;
    private final MedewerkerService medewerkerService;

    public BedrijfController(BedrijfService bedrijfService, TankService tankService, MedewerkerService medewerkerService) {
        this.bedrijfService = bedrijfService;
        this.tankService = tankService;
        this.medewerkerService = medewerkerService;
    }

    //Get all
    @GetMapping("/bedrijf/")
    @CrossOrigin("http://localhost:3000")
    public Iterable<Bedrijf> getAllBedrijf() {
        return bedrijfService.findAll();
    }

    //Get one
    @GetMapping("/bedrijf/{bedrijfsnaam}")
    public Bedrijf getBedrijfByBedrijfsnaam(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam) {
        return bedrijfService.findByBedrijfsnaam(bedrijfsnaam);
    }

    //Create new Bedrijf
    @PostMapping("/bedrijf/")
    public Bedrijf createBedrijf(@Valid @RequestBody Bedrijf bedrijf) {

        if (!bedrijfService.existsByBedrijfsnaam(bedrijf.getBedrijfsnaam())) {

            return bedrijfService.save(bedrijf);
        } else {
            throw new EntityExistsException("Bedrijf", "Bedrijfsnaam", bedrijf.getBedrijfsnaam());
        }
    }

    //Add medewerker bedrijf
    @PutMapping("/bedrijf/addmedewerker/{bedrijfsnaam}")
    public Bedrijf addMedewerker(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam, @Valid @RequestBody Medewerker medewerkerDetails) {

        Bedrijf bedrijf = bedrijfService.findByBedrijfsnaam(bedrijfsnaam);

        bedrijf.addMedewerker(medewerkerDetails);

        medewerkerDetails.setBedrijf(bedrijf);

        return bedrijfService.save(bedrijf);

    }

    @PutMapping("/bedrijf/addtank/{bedrijfsnaam}")
    public Bedrijf addTank(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam, @Valid @RequestBody Tank tankDetails) {
        Bedrijf bedrijf = bedrijfService.findByBedrijfsnaam(bedrijfsnaam);
        return bedrijfService.save(bedrijf);
    }

    //Add contactpersoon van bedrijf
    @PutMapping("/bedrijf/setcontactpersoon/{bedrijfsnaam}")
    public Bedrijf setContactpersoon(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam, @Valid @RequestBody Medewerker medewerkerDetails) {
        Bedrijf bedrijf = bedrijfService.findByBedrijfsnaam(bedrijfsnaam);
        if (medewerkerService.existsByVoornaam(medewerkerDetails.getVoornaam())) {
            Medewerker existingMedewerker = medewerkerService.findByVoornaam(medewerkerDetails.getVoornaam());
            bedrijf.setContactpersoon(existingMedewerker);
            return bedrijfService.save(bedrijf);
        } else {
            bedrijf.setContactpersoon(medewerkerDetails);
            return bedrijfService.save(bedrijf);
        }
    }

    //Update a Bedrijf
    @PutMapping("/bedrijf/{bedrijfsnaam}")
    public Bedrijf updateBedrijf(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam,
                                 @Valid @RequestBody Bedrijf bedrijfDetails) {
        Bedrijf bedrijf = bedrijfService.findByBedrijfsnaam(bedrijfsnaam);
        bedrijf.setBtwNummer(bedrijfDetails.getBtwNummer());
        bedrijf.setAdres(bedrijfDetails.getAdres());
        bedrijf.setContactpersoon(bedrijfDetails.getContactpersoon());
        bedrijf.setKvkNummer(bedrijfDetails.getKvkNummer());
        bedrijf.setRekeningnummer(bedrijfDetails.getRekeningnummer());
        bedrijf.setTelefoonnummer(bedrijfDetails.getTelefoonnummer());
        bedrijf.setVatNummer(bedrijfDetails.getVatNummer());
        bedrijf.setStatus(bedrijfDetails.getStatus());

        return bedrijfService.save(bedrijf);
    }

    //Delete a Bedrijf
    @DeleteMapping("/bedrijf/{bedrijfsnaam}")
    public ResponseEntity<?> deleteBedrijf(@PathVariable(value = "bedrijfsnaam") String bedrijfsnaam) {
        Bedrijf bedrijf = bedrijfService.findByBedrijfsnaam(bedrijfsnaam);

        bedrijfService.delete(bedrijf);
        return ResponseEntity.ok().build();
    }

}
