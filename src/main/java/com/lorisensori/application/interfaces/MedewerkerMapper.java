package com.lorisensori.application.interfaces;

import com.lorisensori.application.DTOs.medewerkerDTOs.MedewerkerDTO;
import com.lorisensori.application.domain.Medewerker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface MedewerkerMapper {

    @Mappings({
            @Mapping(target = "medewerkerVoornaam", source = "voornaam"),
            @Mapping(target = "medewerkerAchternaam", source = "achternaam"),
            @Mapping(target = "medewerkerGebruikersnaam", source = "gebruikersnaam"),
            @Mapping(target = "medewerkerWachtwoord", source = "wachtwoord"),
            @Mapping(target = "medewerkerEmail", source = "email"),
            @Mapping(target = "medewerkerTelefoonnummer", source = "telefoonnummer")
    })
    MedewerkerDTO medewerkerToMedewerkerDTO(Medewerker entity);

    @Mappings({
            @Mapping(target = "voornaam", source = "medewerkerVoornaam"),
            @Mapping(target = "achternaam", source = "medewerkerAchternaam"),
            @Mapping(target = "gebruikersnaam", source = "medewerkerGebruikersnaam"),
            @Mapping(target = "wachtwoord", source = "medewerkerWachtwoord"),
            @Mapping(target = "email", source = "medewerkerEmail"),
            @Mapping(target = "telefoonnummer", source = "medewerkerTelefoonnummer")
    })
    Medewerker medewerkerDTOToMedewerker(MedewerkerDTO dto);
}
