package com.lorisensori.application.DTOs.tankDTOs;

import java.util.Set;

public class TankBedrijfDTO extends TankDTO{
	
	private Set<SensorgegevensDTO> sensorgegevens;
	
	private Set<SensorLogDTO> sensorLog;

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
