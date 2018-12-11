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
        return false;
    }

    @Override
    public Iterable<Bedrijf> findAll() {
        return null;
    }

    @Override
    public Bedrijf findByBedrijfsnaam(String bedrijfsnaam) {
        return null;
    }

    @Override
    public void delete(Bedrijf bedrijf) {
        bedrijfRepository.delete(bedrijf);
    }
}
