package com.lorisensori.application.DTOs.medewerkerDTOs;

import java.io.Serializable;

public class MedewerkerDTO implements Serializable {

    private static final long serialVersionUID = -583528961313526216L;
    private String medewerkerDTOVoornaam, medewerkerDTOAchternaam, medewerkerDTOGebruikersnaam, medewerkerDTOWachtwoord, medewerkerDTOEmail, medewerkerDTOTelefoonnummer;

    ////////////////////////////////////////////////////////////
    //GETTERS AND SETTERS


    public String getmedewerkerDTOVoornaam() {
        return medewerkerDTOVoornaam;
    }

    public void setMedewerkerDTOVoornaam(String medewerkerDTOVoornaam) {
        this.medewerkerDTOVoornaam = medewerkerDTOVoornaam;
    }

    public String getMedewerkerDTOAchternaam() {
        return medewerkerDTOAchternaam;
    }

    public void setMedewerkerDTOAchternaam(String medewerkerDTOAchternaam) {
        this.medewerkerDTOAchternaam = medewerkerDTOAchternaam;
    }

    public String getMedewerkerDTOGebruikersnaam() {
        return medewerkerDTOGebruikersnaam;
    }

    public void setMedewerkerDTOGebruikersnaam(String medewerkerDTOGebruikersnaam) {
        this.medewerkerDTOGebruikersnaam = medewerkerDTOGebruikersnaam;
    }

    public String getMedewerkerDTOWachtwoord() {
        return medewerkerDTOWachtwoord;
    }

    public void setMedewerkerDTOWachtwoord(String medewerkerDTOWachtwoord) {
        this.medewerkerDTOWachtwoord = medewerkerDTOWachtwoord;
    }

    public String getMedewerkerDTOEmail() {
        return medewerkerDTOEmail;
    }

    public void setMedewerkerDTOEmail(String medewerkerDTOEmail) {
        this.medewerkerDTOEmail = medewerkerDTOEmail;
    }

    public String getMedewerkerDTOTelefoonnummer() {
        return medewerkerDTOTelefoonnummer;
    }

    public void setMedewerkerDTOTelefoonnummer(String medewerkerDTOTelefoonnummer) {
        this.medewerkerDTOTelefoonnummer = medewerkerDTOTelefoonnummer;
    }
}
