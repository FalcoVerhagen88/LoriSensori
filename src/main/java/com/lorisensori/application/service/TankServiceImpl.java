package com.lorisensori.application.service;

import com.lorisensori.application.DAO_interfaces.TankRepository;
import com.lorisensori.application.DTOs.tankDTOs.TankDTO;
import com.lorisensori.application.DTOs.tankDTOs.UpdateTankDTO;
import com.lorisensori.application.domain.Tank;
import com.lorisensori.application.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<TankDTO> findAll() {
        return tankRepository.findAll().stream()
                .map(entity -> new TankDTO(entity.getTankId(), entity.getTanknaam(), entity.getType(), entity.getInhoudLiters(),
                        entity.getBouwjaar(), entity.getDiameter(), entity.getLengte(), entity.getGewicht(), entity.getStatus(),
                        entity.getOpeningstijd(), entity.getSluitingstijd(), entity.getMeldingTanken(), entity.getBedrijf(),
                        entity.getSensorgegevens())).collect(Collectors.toList());
    }

    @Transactional
    public TankDTO create(TankDTO tankDTO){

        Tank nieuweTank = new Tank();

        nieuweTank.setTankId(tankDTO.getTankId());
        nieuweTank.setTanknaam(tankDTO.getTanknaam());
        nieuweTank.setType(tankDTO.getType());
        nieuweTank.setInhoudLiters(tankDTO.getInhoudLiters());
        nieuweTank.setBouwjaar(tankDTO.getBouwjaar());
        nieuweTank.setDiameter(tankDTO.getDiameter());
        nieuweTank.setLengte(tankDTO.getLengte());
        nieuweTank.setGewicht(tankDTO.getGewicht());
        nieuweTank.setStatus(tankDTO.getStatus());
        nieuweTank.setOpeningstijd(tankDTO.getOpeningstijd());
        nieuweTank.setSluitingstijd(tankDTO.getSluitingstijd());
        nieuweTank.setMeldingTanken(tankDTO.getMeldingTanken());
        nieuweTank.setBedrijf(tankDTO.getBedrijf());
        nieuweTank.setSensorgegevens(tankDTO.getSensorgegevens());

        Tank opgeslagenTank = tankRepository.saveAndFlush(nieuweTank);

        return new TankDTO(opgeslagenTank.getTankId(), opgeslagenTank.getTanknaam(), opgeslagenTank.getType(),
                opgeslagenTank.getInhoudLiters(), opgeslagenTank.getBouwjaar(), opgeslagenTank.getLengte(), opgeslagenTank.getLengte(),
                opgeslagenTank.getGewicht(), opgeslagenTank.getStatus(), opgeslagenTank.getOpeningstijd(), opgeslagenTank.getSluitingstijd(),
                opgeslagenTank.getMeldingTanken(), opgeslagenTank.getBedrijf(), opgeslagenTank.getSensorgegevens());
    }

    @Transactional
    public UpdateTankDTO update(Long id, UpdateTankDTO updateTankDTO){

        Tank entity = findOneSafe(id);

        entity.setTanknaam(updateTankDTO.getTanknaam());
        entity.setType(updateTankDTO.getType());
        entity.setStatus(updateTankDTO.getStatus());
        entity.setOpeningstijd(updateTankDTO.getOpeningstijd());
        entity.setSluitingstijd(updateTankDTO.getSluitingstijd());

        return new UpdateTankDTO(entity.getTanknaam(), entity.getType(), entity.getStatus(), entity.getOpeningstijd(), entity.getSluitingstijd());
    }

    private Tank findOneSafe(Long id) {
        Tank tank = tankRepository.getOne(id);
        if (tank == null) {
            throw new ResourceNotFoundException("Tank", "Id", id);
        } else {
            return tank;
        }
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
}
