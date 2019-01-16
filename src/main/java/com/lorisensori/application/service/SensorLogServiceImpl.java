package com.lorisensori.application.service;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lorisensori.application.DAO_interfaces.SensorLogRepository;
import com.lorisensori.application.DTOs.tankDTOs.SensorLogDTO;
import com.lorisensori.application.domain.SensorLog;
import com.lorisensori.application.domain.Tank;

@Service("SensorLogService")
public class SensorLogServiceImpl implements SensorLogService {

	private final SensorLogRepository sensorLogRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public SensorLogServiceImpl(SensorLogRepository sensorLogRepository, ModelMapper modelMapper) {
		this.sensorLogRepository = sensorLogRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public Set<SensorLog> findByTank(Tank tank) {
		return sensorLogRepository.findByTank(tank);
	}
	
	@Override
	public SensorLogDTO convertToDto(SensorLog sensorLog) {
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(sensorLog, SensorLogDTO.class);
	}

	@Override
	public SensorLog sensorLogSave(SensorLog sensorlog) {
		sensorLogRepository.save(sensorlog);
		return sensorlog;
	}

}
