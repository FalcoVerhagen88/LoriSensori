package com.lorisensori.application.service;

import com.lorisensori.application.domain.Adres;

public interface AdresService {

    Adres save(Adres adres);

    Adres findByAdresId(Long id);

    Iterable<Adres> findAll();

    void delete(Adres adres);
}
