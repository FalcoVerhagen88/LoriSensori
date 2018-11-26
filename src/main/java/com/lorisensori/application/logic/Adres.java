package com.lorisensori.application.logic;

import com.lorisensori.application.enums.LandEnums;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "adres")
public class Adres implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "adrescode")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adrescode;

    @Column(nullable = false)
    private String straatnaam, postcode, plaatsnaam, huisnummertoevoeging;

    @Column(nullable = false)
    private int huisnummer;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LandEnums land;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Bedrijf bedrijf;

    public Adres() {

    }

    Adres(String straatnaam, int huisnummer, String huisnummertoevoeging, String postcode, String plaatsnaam, LandEnums land) {
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.huisnummertoevoeging = huisnummertoevoeging;
        this.postcode = postcode;
        this.plaatsnaam = plaatsnaam;
        this.land = land;
    }

    public String getHuisnummertoevoeging() {
        return huisnummertoevoeging;
    }

    public void setHuisnummertoevoeging(String huisnummertoevoeging) {
        this.huisnummertoevoeging = huisnummertoevoeging;
    }


    public long getAdrescode() {
        return adrescode;
    }

    public void setAdrescode(long adrescode) {
        this.adrescode = adrescode;
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


}
