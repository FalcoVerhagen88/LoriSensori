package com.lorisensori.application.logic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lorisensori.application.enums.LandEnums;
import com.lorisensori.application.enums.StatusEnums;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "bedrijf")
public class Bedrijf implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 3L;

    @Id
    @Column
    private String bedrijfsnaam;

    @Column
    private String telefoonnummer, rekeningnummer, btwNummer, vatNummer, kvkNummer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adrescode")
    private Adres adres;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contactpersoon")
    private Medewerker contactpersoon;

    @Enumerated(EnumType.STRING)
    private StatusEnums status;

    @OneToMany(mappedBy = "bedrijf", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "medewerkers")
    private List<Medewerker> medewerkers;

    @OneToMany(mappedBy = "bedrijf", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "tanks")
    private List<Tank> tanks;


    public List<Medewerker> getMedewerkers() {
        return medewerkers;
    }

    public void setMedewerkers(List<Medewerker> medewerkers) {
        this.medewerkers = medewerkers;
    }

    public void addMedewerker(Medewerker medewerker) {

        medewerkers.add(medewerker);
    }

    public void addTank(Tank tank) {
        tanks.add(tank);
    }

    public Bedrijf() {

    }

    public StatusEnums getStatus() {
        return status;
    }

    public void setStatus(StatusEnums status) {
        this.status = status;
    }

    public Bedrijf(String bedrijfsnaam, Adres adres, String telefoonnummer, Medewerker contactpersoon, String rekeningnummer, String btwNummer, String vatNummer, String kvkNummer, StatusEnums status) {
        this.bedrijfsnaam = bedrijfsnaam;
        this.adres = adres;
        this.telefoonnummer = telefoonnummer;
        this.contactpersoon = contactpersoon;
        this.rekeningnummer = rekeningnummer;
        this.btwNummer = btwNummer;
        this.vatNummer = vatNummer;
        this.kvkNummer = kvkNummer;
        this.status = status;
    }

    public void setAdres(String straatnaam, int huisnummer, String huisnummertoevoeging, String postcode, String plaatsnaam, LandEnums land) {
//		this.adres = adres;
        adres.setStraatnaam(straatnaam);
        adres.setHuisnummer(huisnummer);
        adres.setHuisnummertoevoeging(huisnummertoevoeging);
        adres.setPostcode(postcode);
        adres.setPlaatsnaam(plaatsnaam);
        adres.setLand(land);
    }

    // getters en setters
    public String getBedrijfsnaam() {
        return bedrijfsnaam;
    }

    public void setBedrijfsnaam(String bedrijfsnaam) {
        this.bedrijfsnaam = bedrijfsnaam;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    public Medewerker getContactpersoon() {
        return contactpersoon;
    }

    public void setContactpersoon(Medewerker contactpersoon) {
        this.contactpersoon = contactpersoon;
    }

    public String getRekeningnummer() {
        return rekeningnummer;
    }

    public void setRekeningnummer(String rekeningnummer) {
        this.rekeningnummer = rekeningnummer;
    }

    public String getBtwNummer() {
        return btwNummer;
    }

    public void setBtwNummer(String btwNummer) {
        this.btwNummer = btwNummer;
    }

    public String getVatNummer() {
        return vatNummer;
    }

    public void setVatNummer(String vatNummer) {
        this.vatNummer = vatNummer;
    }

    public String getKvkNummer() {
        return kvkNummer;
    }

    public void setKvkNummer(String kvkNummer) {
        this.kvkNummer = kvkNummer;
    }

    public List<Tank> getTanks() {
        return tanks;
    }

    public void setTanks(List<Tank> tanks) {
        this.tanks = tanks;
    }



    public String toString() {
        return bedrijfsnaam + "(" + adres + ")";
    }


    public boolean loginverificatie(String username, String password) {
        // TODO Auto-generated method stub
        return username.equals("Tester") && password.equals("!Test00");
    }
}

