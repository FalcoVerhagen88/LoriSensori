package com.lorisensori.application.DTOs.tankDTOs;

import java.util.Set;

public class TankBedrijfDTO {
	
	private Long tankId;
	private String tanknaam;
	
	private Set<SensorgegevensDTO> sensorgegevens;
	
	private Set<SensorLogDTO> sensorLog;

	public Long getTankId() {
		return tankId;
	}

	public void setTankId(Long tankId) {
		this.tankId = tankId;
	}

	public String getTanknaam() {
		return tanknaam;
	}

	public void setTanknaam(String tanknaam) {
		this.tanknaam = tanknaam;
	}
	
	public Set<SensorgegevensDTO> getSensorgegevensDTO() {
		return sensorgegevens;
	}

	public void setSensorgegevensDTO(Set<SensorgegevensDTO> sensorgegevensDTO) {
		this.sensorgegevens = sensorgegevensDTO;
	}

	public Set<SensorLogDTO> getSensorLogDTO() {
		return sensorLog;
	}

	public void setSensorLogDTO(Set<SensorLogDTO> sensorLogDTO) {
		this.sensorLog = sensorLogDTO;
	}
	
}
