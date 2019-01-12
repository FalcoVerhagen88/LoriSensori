package com.lorisensori.application.DTOs.tankDTOs;

import com.lorisensori.application.enums.StatusEnums;

import java.io.Serializable;
import java.util.Date;

public class TankDTO implements Serializable {

    private Long tankId;

    private int tanknummer, diameter, lengte, inhoudLiters, bouwjaar;

    private String tanknaam, type;

    private double gewicht;

    private StatusEnums status;

    private Date openingstijd, sluitingstijd;

    private int meldingTanken;

    //TODO: LoRa informatie toevoegen

    //////////////////////////////////////
    //GETTERS AND SETTERS
    public TankDTO(Long tankId, int tanknummer, int diameter, int lengte, int inhoudLiters, int bouwjaar, String tanknaam, String type, double gewicht, StatusEnums status, Date openingstijd, Date sluitingstijd, int meldingTanken) {
    	this.tankId = tankId;
    	this.tanknummer = tanknummer;
    	this.diameter = diameter;
    	this.lengte = lengte;
    	this.inhoudLiters = inhoudLiters;
    	this.bouwjaar = bouwjaar;
    	this.tanknaam = tanknaam;
    	this.type = type;
    	this.gewicht = gewicht;
    	this.status = status;
    	this.openingstijd = openingstijd;
    	this.sluitingstijd = sluitingstijd;
    	this.meldingTanken = meldingTanken;

    }

    public TankDTO() {

    }

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
