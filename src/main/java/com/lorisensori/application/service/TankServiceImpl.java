package com.lorisensori.application.service;

import com.lorisensori.application.domain.Tank;
import com.lorisensori.application.service.TankService;
import org.springframework.stereotype.Service;

//Business logic goes here NOT in the repository
@Service("tankService")
public class TankServiceImpl implements TankService {
    @Override
    public Tank save(Tank tank) {
        return null;
    }

    @Override
    public boolean existsByTanknaam(String tanknaam) {
        return false;
    }

    @Override
    public Iterable<Tank> findAll() {
        return null;
    }

    @Override
    public Tank findByTanknaam (String tanknaam){return null;}

    @Override
    public Tank findByTankId(Long id){return null;}

    @Override
    public Tank delete(Tank tank) {
        return null;
    }
}
