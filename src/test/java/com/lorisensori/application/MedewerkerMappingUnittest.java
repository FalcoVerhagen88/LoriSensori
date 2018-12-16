package com.lorisensori.application;

import com.lorisensori.application.DTOs.medewerkerDTOs.MedewerkerDTO;
import com.lorisensori.application.DTOs.medewerkerDTOs.UpdateMedewerkerDTO;
import com.lorisensori.application.domain.Medewerker;
import org.junit.Assert;
import org.junit.Test;
import org.modelmapper.ModelMapper;

public class MedewerkerMappingUnittest {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void checkMedewerkerMapping (){

        MedewerkerDTO creation = new MedewerkerDTO();
        creation.setMedewerkerDTOVoornaam("TestVoornaam");
        creation.setMedewerkerDTOAchternaam("TestAchternaam");
        creation.setMedewerkerDTOGebruikersnaam("TestGebruikersnaam");
        creation.setMedewerkerDTOWachtwoord("TestWachtwoord");
        creation.setMedewerkerDTOEmail("TestEmail");
        creation.setMedewerkerDTOTelefoonnummer("TestTelefoonnummer");

        Medewerker medewerker = modelMapper.map(creation, Medewerker.class);

        Assert.assertEquals(creation.getmedewerkerDTOVoornaam(), medewerker.getVoornaam());
        Assert.assertEquals(creation.getMedewerkerDTOAchternaam(), medewerker.getAchternaam());
        Assert.assertEquals(creation.getMedewerkerDTOGebruikersnaam(), medewerker.getGebruikersnaam());
        Assert.assertEquals(creation.getMedewerkerDTOWachtwoord(), medewerker.getWachtwoord());
        Assert.assertEquals(creation.getMedewerkerDTOEmail(), medewerker.getEmail());
        Assert.assertEquals(creation.getMedewerkerDTOTelefoonnummer(), medewerker.getTelefoonnummer());

        UpdateMedewerkerDTO updateMedewerker = new UpdateMedewerkerDTO();
        updateMedewerker.setUpdateMedewerkerDTOGebruikersnaam("Nieuwe TestGebruikersnaam");
        updateMedewerker.setUpdateMedewerkerDTOWachtwoord("Nieuw TestWachtwoord");
        updateMedewerker.setUpdateMedewerkerDTOEmail("Nieuw TestEmail");
        updateMedewerker.setUpdateMedewerkerDTOTelefoonnummer("Nieuw TestTelefoonnummer");

        modelMapper.map(updateMedewerker, medewerker);
        Assert.assertEquals(updateMedewerker.getUpdateMedewerkerDTOGebruikersnaam(), medewerker.getGebruikersnaam());
        Assert.assertEquals(updateMedewerker.getUpdateMedewerkerDTOWachtwoord(), medewerker.getWachtwoord());
        Assert.assertEquals(updateMedewerker.getUpdateMedewerkerDTOEmail(), medewerker.getEmail());
        Assert.assertEquals(updateMedewerker.getUpdateMedewerkerDTOTelefoonnummer(), medewerker.getTelefoonnummer());
    }
}

