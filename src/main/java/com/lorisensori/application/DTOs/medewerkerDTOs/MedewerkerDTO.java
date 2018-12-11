package com.lorisensori.application.DTOs.medewerkerDTOs;

import java.io.Serializable;

public class MedewerkerDTO implements Serializable {

    private static final long serialVersionUID = -583528961313526216L;
    private String medewerkerVoornaam, medewerkerAchternaam, medewerkerGebruikersnaam, medewerkerWachtwoord, medewerkerEmail, medewerkerTelefoonnummer;

    ////////////////////////////////////////////////////////////
    //GETTERS AND SETTERS


    public String getMedewerkerVoornaam() {
        return medewerkerVoornaam;
    }

    public void setMedewerkerVoornaam(String medewerkerVoornaam) {
        medewerkerVoornaam = medewerkerVoornaam;
    }

    public String getMedewerkerAchternaam() {
        return medewerkerAchternaam;
    }

    public void setMedewerkerAchternaam(String medewerkerAchternaam) {
        this.medewerkerAchternaam = medewerkerAchternaam;
    }

    public String getMedewerkerGebruikersnaam() {
        return medewerkerGebruikersnaam;
    }

    public void setMedewerkerGebruikersnaam(String medewerkerGebruikersnaam) {
        this.medewerkerGebruikersnaam = medewerkerGebruikersnaam;
    }

    public String getMedewerkerWachtwoord() {
        return medewerkerWachtwoord;
    }

    public void setMedewerkerWachtwoord(String medewerkerWachtwoord) {
        this.medewerkerWachtwoord = medewerkerWachtwoord;
    }

    public String getMedewerkerEmail() {
        return medewerkerEmail;
    }

    public void setMedewerkerEmail(String medewerkerEmail) {
        this.medewerkerEmail = medewerkerEmail;
    }

    public String getMedewerkerTelefoonnummer() {
        return medewerkerTelefoonnummer;
    }

    public void setMedewerkerTelefoonnummer(String medewerkerTelefoonnummer) {
        medewerkerTelefoonnummer = medewerkerTelefoonnummer;
    }
}
