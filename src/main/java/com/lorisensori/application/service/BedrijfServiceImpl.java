package com.lorisensori.application.service;

import com.lorisensori.application.domain.Bedrijf;
import com.lorisensori.application.DAO_interfaces.BedrijfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Business logic goes here NOT in the repository
@Service("bedrijfService")
public class BedrijfServiceImpl implements BedrijfService {

    private final BedrijfRepository bedrijfRepository;

    @Autowired
    public BedrijfServiceImpl(BedrijfRepository bedrijfRepository) {
        this.bedrijfRepository = bedrijfRepository;
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
}
