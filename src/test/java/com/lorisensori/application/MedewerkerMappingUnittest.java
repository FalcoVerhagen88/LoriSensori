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
        creation.setId(1L);
        creation.setGebruikersnaam("TestGebruikersnaam");
        creation.setVoornaam("TestVoornaam");
        creation.setAchternaam("TestAchternaam");
        creation.setEmail("TestEmail");
        creation.setTelefoonnummer("TestTelefoonnummer");
        creation.setActive(true);
        creation.setEmailVerified(true);
        creation.setRechten(null);

        Medewerker medewerker = modelMapper.map(creation, Medewerker.class);

        Assert.assertEquals(creation.getVoornaam(), medewerker.getVoornaam());
        Assert.assertEquals(creation.getAchternaam(), medewerker.getAchternaam());
        Assert.assertEquals(creation.getGebruikersnaam(), medewerker.getGebruikersnaam());
        Assert.assertEquals(creation.getEmail(), medewerker.getEmail());
        Assert.assertEquals(creation.getTelefoonnummer(), medewerker.getTelefoonnummer());

        UpdateMedewerkerDTO updateMedewerker = new UpdateMedewerkerDTO();
        updateMedewerker.setGebruikersnaam("Nieuwe TestGebruikersnaam");
        updateMedewerker.setEmail("Nieuw TestEmail");
        updateMedewerker.setTelefoonnummer("Nieuw TestTelefoonnummer");

        modelMapper.map(updateMedewerker, medewerker);
        Assert.assertEquals(updateMedewerker.getGebruikersnaam(), medewerker.getGebruikersnaam());
        Assert.assertEquals(updateMedewerker.getEmail(), medewerker.getEmail());
        Assert.assertEquals(updateMedewerker.getTelefoonnummer(), medewerker.getTelefoonnummer());
    }
}

