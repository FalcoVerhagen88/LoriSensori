package com.lorisensori.application.domain;

import com.lorisensori.application.enums.LandEnums;

import javax.persistence.*;

@Entity
@Table(name = "adres")
public class Adres {
    /**
     *
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long adresCode;

    @Column
    private String straatnaam, postcode, plaatsnaam, huisnummertoevoeging;

    @Column(nullable = false)
    private int huisnummer;

    @Enumerated(EnumType.STRING)
    @Column
    private LandEnums land;

    @OneToOne(mappedBy = "adres", optional = false)
    private Bedrijf bedrijf;

    public Adres() {

    }

    public Adres(String straatnaam, int huisnummer, String huisnummertoevoeging, String postcode, String plaatsnaam, LandEnums land) {
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.huisnummertoevoeging = huisnummertoevoeging;
        this.postcode = postcode;
        this.plaatsnaam = plaatsnaam;
        this.land = land;
    }

    ///////////////////////////////////////////////////////////////////
    //GETTERS & SETTERS
    public String getHuisnummertoevoeging() {
        return huisnummertoevoeging;
    }

    public void setHuisnummertoevoeging(String huisnummertoevoeging) {
        this.huisnummertoevoeging = huisnummertoevoeging;
    }


    public long getAdresCode() {
        return adresCode;
    }

    public void setAdresCode(long adrescode) {
        this.adresCode = adrescode;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPlaatsnaam() {
        return plaatsnaam;
    }

    public void setPlaatsnaam(String plaatsnaam) {
        this.plaatsnaam = plaatsnaam;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }

    public LandEnums getLand() {
        return land;
    }

    public void setLand(LandEnums land) {
        this.land = land;
    }

    public String toString() {
        return "Straatnaam: " + straatnaam + ", Huisnummer: " + huisnummer + " " + huisnummertoevoeging + ", Postcode: " + postcode + ", Plaatsnaam: " + plaatsnaam + ", Land: " + land;
    }

    public Bedrijf getBedrijf() {
        return bedrijf;
    }

    public void setBedrijf(Bedrijf bedrijf) {
        this.bedrijf = bedrijf;
    }
}

