package com.lorisensori.application;

import com.lorisensori.application.DTOs.medewerkerDTOs.MedewerkerDTO;
import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.interfaces.MedewerkerMapper;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedewerkerMapperUnitTests {

    MedewerkerMapper mapper = Mappers.getMapper(MedewerkerMapper.class);

    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";

    @Test
    public void givenEmployeeDTOwithDiffNametoEmployee_whenMaps_thenCorrect() {
        MedewerkerDTO dto = new MedewerkerDTO();
        dto.setMedewerkerVoornaam("TestVoornaam");
        dto.setMedewerkerAchternaam("TestAchternaam");
        dto.setMedewerkerGebruikersnaam("TestGebruikersnaam");
        dto.setMedewerkerWachtwoord("TestWachtwoord");
        dto.setMedewerkerEmail("Test@email");
        dto.setMedewerkerTelefoonnummer("0900");

        Medewerker entity = mapper.medewerkerDTOToMedewerker(dto);

        assertEquals(dto.getMedewerkerVoornaam(), entity.getVoornaam());
        assertEquals(dto.getMedewerkerAchternaam(), entity.getAchternaam());
        assertEquals(dto.getMedewerkerGebruikersnaam(), entity.getGebruikersnaam());
        assertEquals(dto.getMedewerkerWachtwoord(), entity.getWachtwoord());
        assertEquals(dto.getMedewerkerEmail(), entity.getEmail());
        assertEquals(dto.getMedewerkerTelefoonnummer(), entity.getTelefoonnummer());

    }

    @Test
    public void givenEmployeewithDiffNametoEmployeeDTO_whenMaps_thenCorrect() {
        Medewerker entity = new Medewerker();
        entity.setVoornaam("TestVoornaam");
        entity.setAchternaam("TestAchternaam");
        entity.setGebruikersnaam("TestGebruikersnaam");
        entity.setWachtwoord("TestWachtwoord");
        entity.setEmail("Test@email");
        entity.setTelefoonnummer("0900");

        MedewerkerDTO dto = mapper.medewerkerToMedewerkerDTO(entity);

        assertEquals(dto.getMedewerkerVoornaam(), entity.getVoornaam());
        assertEquals(dto.getMedewerkerAchternaam(), entity.getAchternaam());
        assertEquals(dto.getMedewerkerGebruikersnaam(), entity.getGebruikersnaam());
        assertEquals(dto.getMedewerkerWachtwoord(), entity.getWachtwoord());
        assertEquals(dto.getMedewerkerEmail(), entity.getEmail());
        assertEquals(dto.getMedewerkerTelefoonnummer(), entity.getTelefoonnummer());
    }
}
