package com.lorisensori.application.logic;

import java.util.Date;

public class Medewerker {

    private String gebruikersnaam, voornaam, achternaam, wachtwoord, email, telefoonnummer;

    private Date createdAt;
    private Date updatedAt;

    /////////////////////////////////////////
    //CONSTRUCTORS
    public Medewerker(String voornaam, String achternaam, String gebruikersnaam, String wachtwoord, String email, String telefoonnummer) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.email = email;
        this.telefoonnummer = telefoonnummer;
    }

    public Medewerker() {

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

    public String toString() {
        return "Voornaam: " + voornaam + ", Achternaam: " + achternaam + ", Gebruikersnaam: " + gebruikersnaam + ", Wachtwoord: " + wachtwoord + ", E-mail: " + email +
                ", Telefoonnummer: " + telefoonnummer + ", Gecreëerd op: " + createdAt + "Laatste update: " + updatedAt;

    }
}
