package com.lorisensori.application.service;

import com.lorisensori.application.DTO.MedewerkerDTO;
import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.DAO_interfaces.MedewerkerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//Business logic goes here NOT in the repository
@Service("medewerkerService")
public class MedewerkerServiceImpl implements MedewerkerService {

    private final MedewerkerRepository medewerkerRepository;

    @Autowired
    public MedewerkerServiceImpl(MedewerkerRepository medewerkerRepository) {
        this.medewerkerRepository = medewerkerRepository;
    }

    @Override
    public Medewerker save(Medewerker medewerker) {
        return medewerkerRepository.save(medewerker);
    }

    @Override
    public boolean existsByVoornaam(String voornaam) {
        return false;
    }

    @Override
    public Iterable<Medewerker> findAll() {
        return medewerkerRepository.findAll();
    }

    @Override
    public Optional<Medewerker> findById(Long id) {
        return medewerkerRepository.findById(id);
    }

    @Override
    public Medewerker findByVoornaam(String voornaam){return medewerkerRepository.findByVoornaam(voornaam);}

    @Override
    public Medewerker delete(Medewerker medewerker) {
        return null;
    }



    public Medewerker convertDTOtoMedewerker(MedewerkerDTO medewerkerDTO, Medewerker medewerker) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.map(medewerkerDTO, medewerker);
        return medewerker;
    }
}
