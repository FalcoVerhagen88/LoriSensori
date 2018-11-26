package com.lorisensori.application.DTO;

import com.lorisensori.application.logic.Medewerker;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.ParseException;

import java.io.Serializable;

public class MedewerkerDTO implements Serializable {

    private static final long serialVersionUID = -583528961313526216L;
    private String gebruikersnaam, voornaam, achternaam, wachtwoord, email, telefoonnummer;

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public MedewerkerDTO convertMedewerkerToDTO(Medewerker medewerker, MedewerkerDTO medewerkerDTO){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.map(medewerker, medewerkerDTO);
        return medewerkerDTO;
    }


    ////////////////////////////////////////////////////////////
    //GETTERS AND SETTERS

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

}
