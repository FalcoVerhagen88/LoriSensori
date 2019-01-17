package com.lorisensori.application.service;

import com.lorisensori.application.DAO_interfaces.TankRepository;
import com.lorisensori.application.DTOs.tankDTOs.TankBedrijfDTO;
import com.lorisensori.application.DTOs.tankDTOs.TankCreateDTO;
import com.lorisensori.application.DTOs.tankDTOs.TankDTO;
import com.lorisensori.application.domain.Sensorgegevens;
import com.lorisensori.application.domain.Bedrijf;

import com.lorisensori.application.domain.Tank;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

//Business logic goes here NOT in the repository
@Service("tankService")
public class TankServiceImpl implements TankService {

    private final TankRepository tankRepository;
    private final ModelMapper modelMapper;

    public TankServiceImpl(TankRepository tankRepository, ModelMapper modelMapper) {
        this.tankRepository = tankRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Tank save(Tank tank) {
        return tankRepository.save(tank);
    }

    @Override
    public boolean existsByTanknaam(String tanknaam) {
        return false;
    }

    @Override
    public List<Tank> findAll() {
        return tankRepository.findAll();
    }

    @Override
    public Tank findByTankId(Long id) {
        return tankRepository.findByTankId(id);
    }

    @Override
    public void delete(Tank tank) {
        tankRepository.delete(tank);
    }

    @Override
    public Tank findByDevId(String devId) {
        return tankRepository.findByDevId(devId);
    }

    @Override
    public void saveSensorgegevens(Sensorgegevens sensorgegevens) {
    }

    @Override
    public Set<Tank> findByBedrijf(Bedrijf bedrijf) {
        return bedrijf.getTanks();
    }

    @Override
    public TankDTO convertToDto(Tank tank) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(tank, TankDTO.class);
    }

    @Override
    public Tank convertToEntity(TankDTO tankDTO) throws ParseException {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(tankDTO, Tank.class);
    }

	@Override
	public TankCreateDTO convertToCreateDto(Tank tank) {
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(tank, TankCreateDTO.class);
	}
	
	@Override 
	public Tank convertToEntityCreate(TankCreateDTO tankCreateDTO) throws ParseException {
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(tankCreateDTO, Tank.class);
	}
//TODO for debugging
    @Override
    public TankBedrijfDTO convertToTankBedrijfDTO(Tank tank) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(tank, TankBedrijfDTO.class);
    }

    @Override
    public void geefstring() {
        System.out.println("HALLO");
    }
}
