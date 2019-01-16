package com.lorisensori.application.DTOs.tankDTOs;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lorisensori.application.enums.StatusEnums;

public class TankCreateDTO {
   private int tanknummer, diameter, lengte, inhoudLiters, bouwjaar;

    private String tanknaam, type;

    private double gewicht;

    private StatusEnums status;

    @JsonFormat(pattern = "HH:mm")
    @DateTimeFormat(pattern = "HH:mm")
    private Date openingstijd, sluitingstijd;

    private int meldingTanken;
    
    private String devId;

	public TankCreateDTO() {
		
	}
    
    public int getTanknummer() {
		return tanknummer;
	}

	public void setTanknummer(int tanknummer) {
		this.tanknummer = tanknummer;
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

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}
    
    
    
}
