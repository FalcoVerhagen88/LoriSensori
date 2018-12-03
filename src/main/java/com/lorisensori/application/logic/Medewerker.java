package com.lorisensori.application.logic;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lorisensori.application.DTO.MedewerkerDTO;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "medewerker")
public class Medewerker implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medewerkerId;

    @Column(name = "gebruikersnaam", nullable = false)
    private String gebruikersnaam;

    @Column(nullable = false)
    private String voornaam, achternaam, wachtwoord, email, telefoonnummer;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "bedrijfsnaam")
    @JsonBackReference(value = "medewerkers")
    private Bedrijf bedrijf;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "tankId")
    private Tank tank;

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

    public Medewerker convertDTOtoMedewerker(MedewerkerDTO medewerkerDTO, Medewerker medewerker) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.map(medewerkerDTO, medewerker);
        return medewerker;
    }

    ///////////////////////////////////////////////////////////////////////////
    //GETTERS AND SETTERS


    @Override
    public String toString() {
        return "Medewerker{" +
                "medewerkerId=" + medewerkerId +
                ", gebruikersnaam='" + gebruikersnaam + '\'' +
                ", voornaam='" + voornaam + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", wachtwoord='" + wachtwoord + '\'' +
                ", email='" + email + '\'' +
                ", telefoonnummer='" + telefoonnummer + '\'' +
                '}';
    }

    public Long getMedewerkerId() {
        return medewerkerId;
    }

    public void setMedewerkerId(Long medewerkerId) {
        this.medewerkerId = medewerkerId;
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
}
