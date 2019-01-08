package com.lorisensori.application.service;

import com.lorisensori.application.DAO_interfaces.TankRepository;
import com.lorisensori.application.domain.Bedrijf;
import com.lorisensori.application.domain.Tank;
import com.lorisensori.application.service.TankService;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

//Business logic goes here NOT in the repository
@Service("tankService")
public class TankServiceImpl implements TankService {

    private final TankRepository tankRepository;

    public TankServiceImpl(TankRepository tankRepository) {
        this.tankRepository = tankRepository;
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
        return null;
    }

    @Override
    public void delete(Tank tank) {
        tankRepository.delete(tank);
    }

	@Override
	public Set<Tank> findByBedrijf(Bedrijf bedrijf) {
		return bedrijf.getTanks();
	}

}
