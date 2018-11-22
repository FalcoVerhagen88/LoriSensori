package com.lorisensori.application.DTO;

import com.lorisensori.application.enums.StatusEnums;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "tank")
public class TankDTO {

    @Id
    @Column(name = "tankid")
    private Long tankId;

    @Column(name = "tanknummer")
    private int tanknummer;

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

    /////////////////////////////////////////////////////
    //GETTERS AND SETTERS


    public Long getTankId() {
        return tankId;
    }

    public void setTankId(Long tankId) {
        this.tankId = tankId;
    }

    public int getTanknummer() {
        return tanknummer;
    }

    public void setTanknummer(int tanknummer) {
        this.tanknummer = tanknummer;
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

