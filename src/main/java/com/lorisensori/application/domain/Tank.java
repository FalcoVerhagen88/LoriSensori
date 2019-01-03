package com.lorisensori.application.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lorisensori.application.enums.StatusEnums;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tank")
public class Tank implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tankId;

    @Column
    private String tanknaam;

    @Column
    private String type;

    @Column
    private int inhoudLiters;

    @Column
    private int bouwjaar;

    @Column
    private int diameter;

    @Column
    private int lengte;

    @Column
    private double gewicht;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusEnums status;

    @Column
    private Date openingstijd;

    @Column
    private Date sluitingstijd;

    @Column
    private int meldingTanken;

    @ManyToOne
    @JoinColumn(name = "bedrijfsnaam")
    @JsonBackReference(value = "tanks")
    private Bedrijf bedrijf;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Sensorgegevens> sensorgegevens;

    //TODO: LoRa informatie toevoegen

    public Tank(String tanknaam, String type, int inhoudLiters, int bouwjaar, int diameter, int lengte, double gewicht, StatusEnums status, Date openingstijd, Date sluitingstijd, int meldingTanken, Bedrijf bedrijf, List<Sensorgegevens> sensorgegevens) {
        this.tanknaam = tanknaam;
        this.type = type;
        this.inhoudLiters = inhoudLiters;
        this.bouwjaar = bouwjaar;
        this.diameter = diameter;
        this.lengte = lengte;
        this.gewicht = gewicht;
        this.status = status;
        this.openingstijd = openingstijd;
        this.sluitingstijd = sluitingstijd;
        this.meldingTanken = meldingTanken;
        this.bedrijf = bedrijf;
        this.sensorgegevens = sensorgegevens;
    }

    public Tank() {

    }

    //////////////////////////////////////
    //GETTERS AND SETTERS

    public Long getTankId() {
        return tankId;
    }

    public void setTankId(Long tankId) {
        this.tankId = tankId;
    }

    public Bedrijf getBedrijf() {
        return bedrijf;
    }

    public void setBedrijf(Bedrijf bedrijf) {
        this.bedrijf = bedrijf;
    }

    public List<Sensorgegevens> getSensorgegevens() {
        return sensorgegevens;
    }

    public void setSensorgegevens(List<Sensorgegevens> sensorgegevens) {
        this.sensorgegevens = sensorgegevens;
    }

    public String getTanknaam() {
        return tanknaam;
    }

    public void setTanknaam(String tanknaam) {
        this.tanknaam = tanknaam;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getInhoudLiters() {
        return inhoudLiters;
    }

    public void setInhoudLiters(int inhoudLiters) {
        this.inhoudLiters = inhoudLiters;
    }

    public int getBouwjaar() {
        return bouwjaar;
    }

    public void setBouwjaar(int bouwjaar) {
        this.bouwjaar = bouwjaar;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getLengte() {
        return lengte;
    }

    public void setLengte(int lengte) {
        this.lengte = lengte;
    }

    public double getGewicht() {
        return gewicht;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public StatusEnums getStatus() {
        return status;
    }

    public void setStatus(StatusEnums status) {
        this.status = status;
    }

    public Date getOpeningstijd() {
        return openingstijd;
    }

    public void setOpeningstijd(Date openingstijd) {
        this.openingstijd = openingstijd;
    }

    public Date getSluitingstijd() {
        return sluitingstijd;
    }

    public void setSluitingstijd(Date sluitingstijd) {
        this.sluitingstijd = sluitingstijd;
    }

    public int getMeldingTanken() {
        return meldingTanken;
    }

    public void setMeldingTanken(int meldingTanken) {
        this.meldingTanken = meldingTanken;
    }

}

