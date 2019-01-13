package com.lorisensori.application.service;

import com.lorisensori.application.DAO_interfaces.SensorgegevensRepository;
import com.lorisensori.application.DTOs.tankDTOs.SensorgegevensDTO;
import com.lorisensori.application.DTOs.tankDTOs.SensorgegevensExtraDTO;
import com.lorisensori.application.domain.Sensorgegevens;
import com.lorisensori.application.domain.Tank;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("SensorgegevensService")
public class SensorgegevensServiceImpl implements SensorgegevensService{

	private final SensorgegevensRepository sensorgegevensRepository;
	private final ModelMapper modelMapper;
	@Autowired
	public SensorgegevensServiceImpl(SensorgegevensRepository sensorgegevensRepository, ModelMapper modelMapper) {
		
		this.sensorgegevensRepository = sensorgegevensRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public Sensorgegevens save(Sensorgegevens sensorgegevens) {
		
		return sensorgegevensRepository.save(sensorgegevens);
	}


	@Override
	public Set<Sensorgegevens> findByTank(Tank tank) {
		return sensorgegevensRepository.findByTank(tank);
	}

	@Override
	public SensorgegevensDTO convertToDto(Sensorgegevens sensorgegevens) {
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(sensorgegevens, SensorgegevensDTO.class);
	}

	@Override
	public Sensorgegevens convertToEntity(SensorgegevensDTO sensorgegevensDTO) {
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(sensorgegevensDTO, Sensorgegevens.class);
	}

	@Override
	public SensorgegevensExtraDTO convertToExtraDto(Sensorgegevens sensorgegevens) {
		return modelMapper.map(sensorgegevens, SensorgegevensExtraDTO.class);
	}
	
}


