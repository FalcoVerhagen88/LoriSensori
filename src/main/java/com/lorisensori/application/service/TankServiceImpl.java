package com.lorisensori.application.service;

import com.lorisensori.application.DAO_interfaces.TankRepository;
import com.lorisensori.application.DTOs.tankDTOs.TankDTO;
import com.lorisensori.application.domain.Bedrijf;
import com.lorisensori.application.domain.Sensorgegevens;
import com.lorisensori.application.domain.Tank;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

//Business logic goes here NOT in the repository
@Service("tankService")
public class TankServiceImpl implements TankService {

    private final TankRepository tankRepository;

    private final BedrijfService bedrijfService;

    private final ModelMapper modelMapper;

    public TankServiceImpl(TankRepository tankRepository, BedrijfService bedrijfService, ModelMapper modelMapper) {
        this.tankRepository = tankRepository;
        this.bedrijfService = bedrijfService;
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
    public Tank findByTanknaam(String tanknaam) {
        return null;
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
    public TankDTO updateTank(TankDTO tankDTO) {

        Tank tank = convertToEntity(tankDTO);

        System.out.println(tankDTO.getTanknaam());

        tank.setBouwjaar(tankDTO.getBouwjaar());
        tank.setDiameter(tankDTO.getDiameter());
        tank.setGewicht(tankDTO.getGewicht());
        tank.setInhoudLiters(tankDTO.getInhoudLiters());
        tank.setLengte(tankDTO.getLengte());
        tank.setMeldingTanken(tankDTO.getMeldingTanken());
        tank.setOpeningstijd(tankDTO.getOpeningstijd());
        tank.setSluitingstijd(tankDTO.getSluitingstijd());
        tank.setTanknummer(tankDTO.getTanknummer());
        tank.setType(tankDTO.getType());
        tank.setStatus(tankDTO.getStatus());
        tank.setTanknaam(tankDTO.getTanknaam());

        Tank updatedTank = tankRepository.save(tank);
        return convertToDto(updatedTank);
    }

    public TankDTO convertToDto(Tank tank) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(tank, TankDTO.class);
    }

    public Tank convertToEntity(TankDTO tankDTO){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(tankDTO, Tank.class);
    }
}
