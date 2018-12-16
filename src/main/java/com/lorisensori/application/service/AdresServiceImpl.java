package com.lorisensori.application.service;

import com.lorisensori.application.DAO_interfaces.AdresRepository;
import com.lorisensori.application.domain.Adres;
import org.springframework.stereotype.Service;

//Business logic goes here NOT in the repository
@Service
public class AdresServiceImpl implements AdresService {

    private final AdresRepository adresRepository;

    public AdresServiceImpl(AdresRepository adresRepository) {
        this.adresRepository = adresRepository;
    }

    @Override
    public Adres save(Adres adres) {
        return adresRepository.save(adres);
    }

    @Override
    public Adres findByAdresId(Long adresCode) {
        return adresRepository.findByAdresCode(adresCode);
    }

    @Override
    public Iterable<Adres> findAll() {
        return null;
    }

    @Override
    public void delete(Adres adres) {

    }
}
