package com.lorisensori.application.logic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lorisensori.application.DTO.MedewerkerDTO;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "medewerker")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Medewerker implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medewerkerId;

    @Column(name = "gebruikersnaam", nullable = false)
    private String gebruikersnaam;

    @Column(nullable = false)
    private String voornaam, achternaam, wachtwoord, email, telefoonnummer;

    @ManyToOne
    @JoinColumn(name = "bedrijfsnaam")
    private Bedrijf bedrijf;

    @ManyToOne
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

//    public Long getBedrijfId(){return bedrijf.get}

    public Long getMedewerkerId() {
        return medewerkerId;
    }

    public void setMedewerkerId(Long medewerkerId) {
        this.medewerkerId = medewerkerId;
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
                ", Telefoonnummer: " + telefoonnummer;

    }
}
