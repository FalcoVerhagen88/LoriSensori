package com.lorisensori.application.service;

import com.lorisensori.application.DAO_interfaces.MedewerkerRepository;
import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.exceptions.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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
        if (!existsByVoornaam(medewerker.getVoornaam())) {
            return medewerkerRepository.save(medewerker);
        } else {
            throw new EntityExistsException("Medewerker", "Voornaam", medewerker.getVoornaam());
        }
    }

    @Override
    public boolean existsByVoornaam(String voornaam) {

        return medewerkerRepository.findByVoornaam(voornaam) != null;
    }

    @Override
    public Iterable<Medewerker> findAll() {
        if (medewerkerRepository.findAll() == null) {
            return null;
        }
        return medewerkerRepository.findAll();
    }

    @Override
    public Optional<Medewerker> findById(Long id) {
        return medewerkerRepository.findById(id);
    }

    @Override
    public Medewerker findByVoornaam(String voornaam) {
        return medewerkerRepository.findByVoornaam(voornaam);
    }

    @Override
    public Optional<Medewerker> findByGebruikersnaam(String gebruikersnaam) {
        return medewerkerRepository.findByGebruikersnaam(gebruikersnaam);
    }

    @Override
    public void delete(Medewerker medewerker) {
        medewerkerRepository.delete(medewerker);
    }

}
