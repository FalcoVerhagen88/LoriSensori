package com.lorisensori.application.DTOs.tankDTOs;

import java.util.Set;

public class TankBedrijfDTO {

	private TankDTO tankDTO;
	
	private Set<SensorgegevensDTO> sensorgegevensDTO;
	
	private Set<SensorLogDTO> sensorLogDTO;

	public TankDTO getTankDTO() {
		return tankDTO;
	}

	public void setTankDTO(TankDTO tankDTO) {
		this.tankDTO = tankDTO;
	}

	public Set<SensorgegevensDTO> getSensorgegevensDTO() {
		return sensorgegevensDTO;
	}

	public void setSensorgegevensDTO(Set<SensorgegevensDTO> sensorgegevensDTO) {
		this.sensorgegevensDTO = sensorgegevensDTO;
	}

	public Set<SensorLogDTO> getSensorLogDTO() {
		return sensorLogDTO;
	}

	public void setSensorLogDTO(Set<SensorLogDTO> sensorLogDTO) {
		this.sensorLogDTO = sensorLogDTO;
	}
	
	
}
