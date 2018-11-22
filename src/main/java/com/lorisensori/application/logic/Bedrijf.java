package com.lorisensori.application.logic;

import com.lorisensori.application.enums.LandEnums;
import com.lorisensori.application.enums.StatusEnums;

import java.util.List;

public class Bedrijf {

    private String bedrijfsnaam;
    private String telefoonnummer;
    private String rekeningnummer;
    private String btwNummer;
    private String kvkNummer;
    private Adres adres;
    private Medewerker contactpersoon;
    private StatusEnums status;
    private List<Medewerker> medewerkers;
    private List<Tank> tanks;

    /////////////////////////////////////////
    //CONSTRUCTORS
    public Bedrijf(String bedrijfsnaam, String telefoonnummer, String rekeningnummer, String btwNummer, String kvkNummer,
                   Adres adres, Medewerker contactpersoon, StatusEnums status, List<Medewerker> medewerkers, List<Tank> tanks) {
        this.bedrijfsnaam = bedrijfsnaam;
        this.telefoonnummer = telefoonnummer;
        this.rekeningnummer = rekeningnummer;
        this.btwNummer = btwNummer;
        this.kvkNummer = kvkNummer;
        this.adres = adres;
        this.contactpersoon = contactpersoon;
        this.status = status;
        this.medewerkers = medewerkers;
        this.tanks = tanks;
    }

    public Bedrijf() {
    }

    //////////////////////////////////////////////
    //GETTERS & SETTERS
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

    public String getKvkNummer() {
        return kvkNummer;
    }

    public void setKvkNummer(String kvkNummer) {
        this.kvkNummer = kvkNummer;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public Medewerker getContactpersoon() {
        return contactpersoon;
    }

    public void setContactpersoon(Medewerker contactpersoon) {
        this.contactpersoon = contactpersoon;
    }

    public StatusEnums getStatus() {
        return status;
    }

    public void setStatus(StatusEnums status) {
        this.status = status;
    }

    public List<Tank> getTanks() {
        return tanks;
    }

    public void setTanks(List<Tank> tanks) {
        this.tanks = tanks;
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

    public void addTank(Tank tank){tanks.add(tank);}

    public void setAdres(String straatnaam, int huisnummer, String huisnummertoevoeging, String postcode, String plaatsnaam, LandEnums land) {
//		this.adres = adres;
        adres.setStraatnaam(straatnaam);
        adres.setHuisnummer(huisnummer);
        adres.setHuisnummertoevoeging(huisnummertoevoeging);
        adres.setPostcode(postcode);
        adres.setPlaatsnaam(plaatsnaam);
        adres.setLand(land);
    }
}
