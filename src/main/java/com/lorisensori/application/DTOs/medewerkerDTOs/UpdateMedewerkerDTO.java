package com.lorisensori.application.DTOs.medewerkerDTOs;

public class UpdateMedewerkerDTO {

    private String gebruikersnaam, voornaam, achternaam, email, telefoonnummer;

    private Boolean active, isEmailVerified;

    public UpdateMedewerkerDTO(String gebruikersnaam, String voornaam, String achternaam, String email, String telefoonnummer, Boolean active, Boolean isEmailVerified) {
        this.gebruikersnaam = gebruikersnaam;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.email = email;
        this.telefoonnummer = telefoonnummer;
        this.active = active;
        this.isEmailVerified = isEmailVerified;
    }

    public UpdateMedewerkerDTO(){}

    ////////////////////////////////////////////////////////////
    //GETTERS AND SETTERS


    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        isEmailVerified = emailVerified;
    }
}
