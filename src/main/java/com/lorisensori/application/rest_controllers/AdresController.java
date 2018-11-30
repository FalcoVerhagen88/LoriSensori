package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.exceptions.ResourceNotFoundException;
import com.lorisensori.application.interfaces.AdresRepository;
import com.lorisensori.application.logic.Adres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//this annotation is a combination of Spring’s @Controller and @ResponseBody annotations. The @Controller annotation is used to define a controller and the @ResponseBody annotation is used to indicate that the return value of a method should be used as the response body of the request.
@RequestMapping("/api")
//this annotation  declares that the url for all the apis in this controller will start with /api.
public class AdresController {

    private final AdresRepository adresRepository;

    @Autowired
    public AdresController(AdresRepository adresRepository) {
        this.adresRepository = adresRepository;
    }

    //Get all Adressen
    @GetMapping("/adres/")
    public List<Adres> getAllAdres() {
        return adresRepository.findAll();
    }

    //Get a single Adres
    @GetMapping("/adres/{adrescode}")
    public Adres getAdresByadrescode(@PathVariable(value = "adrescode") Long adresId) {
        return adresRepository.findById(adresId)
                .orElseThrow(() -> new ResourceNotFoundException("Adres", "adrescode", adresId));
    }

    //Create a new Adres
    @PostMapping("/adres/")
    public Adres createAdres(@Valid @RequestBody Adres adres) {
        return adresRepository.save(adres);
    }

    //Update a Adres
    @PutMapping("adres/{adrescode}")
    public Adres updateAdres(@PathVariable(value = "adrescode") Long adresId,
                             @Valid @RequestBody Adres adresDetails) {
        Adres adres = adresRepository.findById(adresId)
                .orElseThrow(() -> new ResourceNotFoundException("Adres", "adrescode", adresId));
        adres.setPlaatsnaam(adresDetails.getPlaatsnaam());
        adres.setHuisnummer(adresDetails.getHuisnummer());
        adres.setHuisnummertoevoeging(adresDetails.getHuisnummertoevoeging());
        adres.setStraatnaam(adresDetails.getStraatnaam());
        adres.setPostcode(adresDetails.getPostcode());
        adres.setLand(adresDetails.getLand());

        Adres updatedAdres = adresRepository.save(adres);
        return updatedAdres;
    }

    //Delete a Adres
    @DeleteMapping("adres/{adrescode}")
    public ResponseEntity<?> deleteAdres(@PathVariable(value = "adrescode") Long adresId) {
        Adres adres = adresRepository.findById(adresId)
                .orElseThrow(() -> new ResourceNotFoundException("Adres", "adrescode", adresId));
        adresRepository.delete(adres);
        return ResponseEntity.ok().build();

    }
}
