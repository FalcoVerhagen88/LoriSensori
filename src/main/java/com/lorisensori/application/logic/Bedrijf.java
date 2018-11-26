package com.lorisensori.application.logic;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "bedrijf")
public class Bedrijf implements Serializable {

    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bedrijfId;

    @Column(name = "bedrijfsnaam")
    private String bedrijfsnaam;

    @Column(nullable = false)
    private String telefoonnummer, rekeningnummer, btwNummer, vatNummer, kvkNummer;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contactpersoon")
    private Medewerker contactpersoon;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @ElementCollection
    private List<Medewerker> medewerkers;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @ElementCollection
    private List<Tank> tanks;


    /////////////////////////////////////////
    //CONSTRUCTORS
    public Bedrijf(String bedrijfsnaam, String telefoonnummer, String rekeningnummer, String btwNummer, String kvkNummer,
                   Medewerker contactpersoon, List<Medewerker> medewerkers, List<Tank> tanks) {
        this.bedrijfsnaam = bedrijfsnaam;
        this.telefoonnummer = telefoonnummer;
        this.rekeningnummer = rekeningnummer;
        this.btwNummer = btwNummer;
        this.kvkNummer = kvkNummer;
        this.contactpersoon = contactpersoon;
        this.medewerkers = medewerkers;
        this.tanks = tanks;
    }

    public Bedrijf() {
    }



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


    ///////////////////////////////////////////////////////////////
    //GETTERS AND SETTERS


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getBedrijfId() {
        return bedrijfId;
    }

    public void setBedrijfId(Long bedrijfId) {
        this.bedrijfId = bedrijfId;
    }

    public String getBedrijfsnaam() {
        return bedrijfsnaam;
    }

    public void setBedrijfsnaam(String bedrijfsnaam) {
        this.bedrijfsnaam = bedrijfsnaam;
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

    public boolean loginverificatie(String username, String password) {
        // TODO Auto-generated method stub
        if (username == "Tester" && password == "!Test00") {
            return true;
        }
        return false;
    }
}

