package com.lorisensori.application.service;

import com.lorisensori.application.DTOs.bedrijfDTOs.BedrijfDTO;
import com.lorisensori.application.domain.Bedrijf;
import com.lorisensori.application.DAO_interfaces.BedrijfRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Business logic goes here NOT in the repository
@Service("bedrijfService")
public class BedrijfServiceImpl implements BedrijfService {

    private final BedrijfRepository bedrijfRepository;
    private final MedewerkerService medewerkerService;
    private final ModelMapper modelMapper;

    @Autowired
    public BedrijfServiceImpl(BedrijfRepository bedrijfRepository, MedewerkerService medewerkerService, ModelMapper modelMapper) {
        this.bedrijfRepository = bedrijfRepository;
        this.medewerkerService = medewerkerService;
        this.modelMapper = modelMapper;
    }


    @Override
    public Bedrijf save(Bedrijf bedrijf) {
        return bedrijfRepository.save(bedrijf);
    }

    @Override
    public boolean existsByBedrijfsnaam(String bedrijfsnaam) {

        return bedrijfRepository.findByBedrijfsnaam(bedrijfsnaam) != null;
    }

    @Override
    public Iterable<Bedrijf> findAll() {
        return bedrijfRepository.findAll();
    }

    @Override
    public Bedrijf findByBedrijfsnaam(String bedrijfsnaam) {

        return bedrijfRepository.findByBedrijfsnaam(bedrijfsnaam);
    }

    @Override
    public void delete(Bedrijf bedrijf) {
        bedrijfRepository.delete(bedrijf);
    }

    @Override
    public BedrijfDTO convertToDto(Bedrijf bedrijf) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(bedrijf, BedrijfDTO.class);
    }

    @Override
    public Bedrijf convertToEntity(BedrijfDTO bedrijfDTO) throws ParseException {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(bedrijfDTO, Bedrijf.class);
    }

}
