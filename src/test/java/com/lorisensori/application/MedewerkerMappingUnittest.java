package com.lorisensori.application;

import com.lorisensori.application.DTOs.medewerkerDTOs.MedewerkerDTO;
import com.lorisensori.application.DTOs.medewerkerDTOs.UpdateMedewerkerDTO;
import com.lorisensori.application.domain.Medewerker;
import org.junit.Assert;
import org.junit.Test;
import org.modelmapper.ModelMapper;

public class MedewerkerMappingUnittest {

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void checkMedewerkerMapping (){

        MedewerkerDTO medewerkerDTO = new MedewerkerDTO();
        medewerkerDTO.setId(1L);
        medewerkerDTO.setGebruikersnaam("TestGebruikersnaam");
        medewerkerDTO.setVoornaam("TestVoornaam");
        medewerkerDTO.setAchternaam("TestAchternaam");
        medewerkerDTO.setEmail("TestEmail");
        medewerkerDTO.setTelefoonnummer("TestTelefoonnummer");
        medewerkerDTO.setActive(true);
        medewerkerDTO.setEmailVerified(true);
        medewerkerDTO.setRechten(null);

        Medewerker medewerker = modelMapper.map(medewerkerDTO, Medewerker.class);

        Assert.assertEquals(medewerkerDTO.getVoornaam(), medewerker.getVoornaam());
        Assert.assertEquals(medewerkerDTO.getAchternaam(), medewerker.getAchternaam());
        Assert.assertEquals(medewerkerDTO.getGebruikersnaam(), medewerker.getGebruikersnaam());
        Assert.assertEquals(medewerkerDTO.getEmail(), medewerker.getEmail());
        Assert.assertEquals(medewerkerDTO.getTelefoonnummer(), medewerker.getTelefoonnummer());

        UpdateMedewerkerDTO updateMedewerker = new UpdateMedewerkerDTO();
        updateMedewerker.setGebruikersnaam("Nieuwe TestGebruikersnaam");
        updateMedewerker.setEmail("Nieuwe TestEmail");
        updateMedewerker.setTelefoonnummer("Nieuwe TestTelefoonnummer");

        modelMapper.map(updateMedewerker, medewerker);
        Assert.assertEquals(updateMedewerker.getGebruikersnaam(), medewerker.getGebruikersnaam());
        Assert.assertEquals(updateMedewerker.getEmail(), medewerker.getEmail());
        Assert.assertEquals(updateMedewerker.getTelefoonnummer(), medewerker.getTelefoonnummer());
    }
}

