package com.lorisensori.application.rest_controllers;

import com.lorisensori.application.domain.Adres;
import com.lorisensori.application.service.AdresService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AdresController {

    private final AdresService adresService;

    public AdresController(AdresService adresService) {
        this.adresService = adresService;
    }

    //Get all Adressen
    @GetMapping("/adres/")
    public Iterable<Adres> getAllAdres() {
        return adresService.findAll();
    }

    //Get a single Adres
    @GetMapping("/adres/{adrescode}")
    public Adres getAdresByadrescode(@PathVariable(value = "adrescode") Long adresId) {
        return adresService.findByAdresId(adresId);
    }

    //Create a new Adres
    @PostMapping("/adres/")
    public Adres createAdres(@Valid @RequestBody Adres adres) {
        return adresService.save(adres);
    }

    //Update a Adres
    @PutMapping("adres/{adrescode}")
    public Adres updateAdres(@PathVariable(value = "adrescode") Long adresId,
                             @Valid @RequestBody Adres adresDetails) {
        Adres adres = adresService.findByAdresId(adresId);

        adres.setPlaatsnaam(adresDetails.getPlaatsnaam());
        adres.setHuisnummer(adresDetails.getHuisnummer());
        adres.setHuisnummertoevoeging(adresDetails.getHuisnummertoevoeging());
        adres.setStraatnaam(adresDetails.getStraatnaam());
        adres.setPostcode(adresDetails.getPostcode());
        adres.setLand(adresDetails.getLand());

        Adres updatedAdres = adresService.save(adres);
        return updatedAdres;
    }

    //Delete a Adres
    @DeleteMapping("adres/{adrescode}")
    public ResponseEntity<?> deleteAdres(@PathVariable(value = "adrescode") Long adresId) {
        Adres adres = adresService.findByAdresId(adresId);
        adresService.delete(adres);
        return ResponseEntity.ok().build();

    }
}
