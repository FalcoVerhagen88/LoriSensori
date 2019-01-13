package com.lorisensori.application.service;

import java.util.Set;

import com.lorisensori.application.DTOs.tankDTOs.SensorLogDTO;
import com.lorisensori.application.domain.SensorLog;

public interface SensorLogService {
//	Set<SensorLog> findNewest25();

	SensorLogDTO convertToDto(SensorLog sensorLog);
}
