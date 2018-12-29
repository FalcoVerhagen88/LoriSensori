package com.lorisensori.application.DTOs.medewerkerDTOs;

import com.lorisensori.application.domain.Bedrijf;
import com.lorisensori.application.domain.Recht;
import com.lorisensori.application.domain.Tank;

import java.io.Serializable;
import java.util.Set;

public class MedewerkerDTO implements Serializable {

    private static final long serialVersionUID = -583528961313526216L;

    private Long id;

    private String gebruikersnaam, voornaam, achternaam, password, email, telefoonnummer;

    private Bedrijf bedrijf;

    private Tank tank;

    private Boolean active;

    private Set<Recht> rechten;

    private Boolean isEmailVerified;

    public MedewerkerDTO(Long id, String gebruikersnaam, String voornaam, String achternaam, String password, String email, String telefoonnummer, Bedrijf bedrijf, Tank tank, Boolean active, Set<Recht> rechten, Boolean emailVerified) {
    }

    public MedewerkerDTO(){}



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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Bedrijf getBedrijf() {
        return bedrijf;
    }

    public void setBedrijf(Bedrijf bedrijf) {
        this.bedrijf = bedrijf;
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
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
}
