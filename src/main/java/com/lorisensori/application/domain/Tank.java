package com.lorisensori.application.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lorisensori.application.enums.StatusEnums;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Tank implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tankId;

    @NotBlank
    @Column(name = "tanknaam")
    private String tanknaam;

    private String type;

    private int inhoudLiters;

    private int bouwjaar;

    private int diameter;
    private int lengte;
    private double gewicht;

    @Enumerated(EnumType.STRING)
    private StatusEnums status;

    private Date openingstijd;
    private Date sluitingstijd;

    private int meldingTanken;

    @ManyToOne
    @JoinColumn(name = "bedrijfsnaam")
    @JsonBackReference(value = "tanks")
    private Bedrijf bedrijf;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Sensorgegevens> sensorgegevens;

    //TODO: LoRa informatie toevoegen

    Tank() {

    }

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

    public Long getTankid() {
        return tankId;
    }

    public void setTankid(Long tankId) {
        this.tankId = tankId;
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

