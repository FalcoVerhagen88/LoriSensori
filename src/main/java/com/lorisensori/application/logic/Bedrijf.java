package com.lorisensori.application.logic;

import com.lorisensori.application.enums.LandEnums;
import com.lorisensori.application.enums.StatusEnums;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "Bedrijf")
public class Bedrijf implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 3L;

    @Id
    @Column(name = "bedrijfsnaam")
    private String bedrijfsnaam;

    @Column(name = "telefoonnummer")
    @NotBlank
    private String telefoonnummer;

    @Column(name = "rekeningnummer")
    @NotBlank
    private String rekeningnummer;

    @Column(name = "btwNummer")
    @NotBlank
    private String btwNummer;

    @Column(name = "vatNummer")
    @NotBlank
    private String vatNummer;

    @Column(name = "kvkNummer")
    @NotBlank
    private String kvkNummer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adrescode")
    private Adres adres;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contactpersoon")
    private Medewerker contactpersoon;

    @Enumerated(EnumType.STRING)
    private StatusEnums status;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "medewerkers_bedrijf",
            joinColumns = @JoinColumn(name = "BEDRIJF_BEDRIJFSNAAM", referencedColumnName = "bedrijfsnaam"),
            inverseJoinColumns = @JoinColumn(name = "MEDEWERKER_GEBRUIKERSNAAM", referencedColumnName = "gebruikersnaam"))
    private List<Medewerker> medewerkers;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bedrijfsnaam")
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

    public void addTank(Tank tank) {
        tanks.add(tank);
    }

    public String toString() {
        return bedrijfsnaam + "(" + adres + ")";
    }


    public boolean loginverificatie(String username, String password) {
        // TODO Auto-generated method stub
        return username.equals("Tester") && password.equals("!Test00");
    }
}

