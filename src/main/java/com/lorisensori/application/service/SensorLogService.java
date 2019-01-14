package com.lorisensori.application.service;

import java.util.Set;

import com.lorisensori.application.DTOs.tankDTOs.SensorLogDTO;
import com.lorisensori.application.domain.SensorLog;
import com.lorisensori.application.domain.Tank;

public interface SensorLogService {
//	Set<SensorLog> findNewest25();

	SensorLogDTO convertToDto(SensorLog sensorLog);

	Set<SensorLog> findByTank(Tank findByTankId);
}
