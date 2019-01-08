package com.lorisensori.application.DTOs.tankDTOs;

import java.io.Serializable;
import java.util.Date;

import com.lorisensori.application.domain.Medewerker;

public class SensorgegevensDTO implements Serializable {
	private Long sensorId;
	private boolean slotStatus;
	private int dieselniveau;
	private int accuniveau;
	private int vermogenZonnepaneel;
	private int gpsBreedtegraad;
	private int gpsLengtegraad;
	private Date timestamp;
	private Medewerker medewerkerSlot;
	
	public SensorgegevensDTO() {
		
	}

	public Long getSensorId() {
		return sensorId;
	}

	public void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}

	public boolean isSlotStatus() {
		return slotStatus;
	}

	public void setSlotStatus(boolean slotStatus) {
		this.slotStatus = slotStatus;
	}

	public int getDieselniveau() {
		return dieselniveau;
	}

	public void setDieselniveau(int dieselniveau) {
		this.dieselniveau = dieselniveau;
	}

	public int getAccuniveau() {
		return accuniveau;
	}

	public void setAccuniveau(int accuniveau) {
		this.accuniveau = accuniveau;
	}

	public int getVermogenZonnepaneel() {
		return vermogenZonnepaneel;
	}

	public void setVermogenZonnepaneel(int vermogenZonnepaneel) {
		this.vermogenZonnepaneel = vermogenZonnepaneel;
	}

	public int getGpsBreedtegraad() {
		return gpsBreedtegraad;
	}

	public void setGpsBreedtegraad(int gpsBreedtegraad) {
		this.gpsBreedtegraad = gpsBreedtegraad;
	}

	public int getGpsLengtegraad() {
		return gpsLengtegraad;
	}

	public void setGpsLengtegraad(int gpsLengtegraad) {
		this.gpsLengtegraad = gpsLengtegraad;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Medewerker getMedewerkerSlot() {
		return medewerkerSlot;
	}

	public void setMedewerkerSlot(Medewerker medewerkerSlot) {
		this.medewerkerSlot = medewerkerSlot;
	}

	
}
