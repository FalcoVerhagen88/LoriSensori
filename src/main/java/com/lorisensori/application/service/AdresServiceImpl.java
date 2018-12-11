package com.lorisensori.application.service;

import com.lorisensori.application.domain.Adres;
import org.springframework.stereotype.Service;

//Business logic goes here NOT in the repository
@Service
public class AdresServiceImpl implements AdresService {
    @Override
    public Adres save(Adres adres) {
        return null;
    }

    @Override
    public Adres findByAdresId(Long id) {
        return null;
    }

    @Override
    public Iterable<Adres> findAll() {
        return null;
    }

    @Override
    public void delete(Adres adres) {

    }
}
