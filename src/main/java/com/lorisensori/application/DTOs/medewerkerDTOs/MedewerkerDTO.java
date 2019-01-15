package com.lorisensori.application.DTOs.medewerkerDTOs;

import com.lorisensori.application.domain.Bedrijf;
import com.lorisensori.application.domain.Recht;
import com.lorisensori.application.domain.Tank;

import java.io.Serializable;
import java.util.Set;

public class MedewerkerDTO implements Serializable {

    private Long id;

    private String gebruikersnaam, voornaam, achternaam, email, telefoonnummer;

    private String bedrijfsnaam;

    private Boolean active;

    private Set<Recht> rechten;

    private Boolean isEmailVerified;

    public MedewerkerDTO() {
    }


    ////////////////////////////////////////////////////////////
    //GETTERS AND SETTERS


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<Recht> getRechten() {
        return rechten;
    }

    public void setRechten(Set<Recht> rechten) {
        this.rechten = rechten;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        isEmailVerified = emailVerified;
    }


    public String getBedrijfsnaam() {
        return bedrijfsnaam;
    }


    public void setBedrijfsnaam(String bedrijfsnaam) {
        this.bedrijfsnaam = bedrijfsnaam;
    }


}
