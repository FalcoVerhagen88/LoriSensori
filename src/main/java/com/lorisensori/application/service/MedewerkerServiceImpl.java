package com.lorisensori.application.service;

import com.lorisensori.application.DAO_interfaces.MedewerkerRepository;
import com.lorisensori.application.DTOs.medewerkerDTOs.MedewerkerDTO;
import com.lorisensori.application.DTOs.medewerkerDTOs.UpdateMedewerkerDTO;
import com.lorisensori.application.domain.Medewerker;
import com.lorisensori.application.exceptions.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<MedewerkerDTO> findAll() {
        return medewerkerRepository.findAll().stream()
                .map(entity -> new MedewerkerDTO(entity.getId(), entity.getGebruikersnaam(), entity.getVoornaam(), entity.getAchternaam(),
                        entity.getPassword(), entity.getEmail(), entity.getTelefoonnummer(), entity.getBedrijf(), entity.getTank(),
                        entity.getActive(), entity.getRechten(), entity.getEmailVerified())).collect(Collectors.toList());
    }

    @Transactional
    public MedewerkerDTO create(MedewerkerDTO medewerkerDTO) {

        Medewerker nieuweMedewerker = new Medewerker();

        nieuweMedewerker.setId(medewerkerDTO.getId());
        nieuweMedewerker.setGebruikersnaam(medewerkerDTO.getGebruikersnaam());
        nieuweMedewerker.setVoornaam(medewerkerDTO.getVoornaam());
        nieuweMedewerker.setAchternaam(medewerkerDTO.getAchternaam());
        nieuweMedewerker.setPassword(medewerkerDTO.getPassword());
        nieuweMedewerker.setEmail(medewerkerDTO.getEmail());
        nieuweMedewerker.setTelefoonnummer(medewerkerDTO.getTelefoonnummer());
        nieuweMedewerker.setBedrijf(medewerkerDTO.getBedrijf());
        nieuweMedewerker.setTank(medewerkerDTO.getTank());
        nieuweMedewerker.setActive(medewerkerDTO.getActive());
        nieuweMedewerker.setRechten(medewerkerDTO.getRechten());
        nieuweMedewerker.setEmailVerified(medewerkerDTO.getEmailVerified());

        Medewerker opgeslagenMedewerker = medewerkerRepository.saveAndFlush(nieuweMedewerker);
        return new MedewerkerDTO(opgeslagenMedewerker.getId(), opgeslagenMedewerker.getGebruikersnaam(), opgeslagenMedewerker.getVoornaam(),
                opgeslagenMedewerker.getAchternaam(), opgeslagenMedewerker.getPassword(), opgeslagenMedewerker.getEmail(), opgeslagenMedewerker.getTelefoonnummer(),
                opgeslagenMedewerker.getBedrijf(), opgeslagenMedewerker.getTank(), opgeslagenMedewerker.getActive(), opgeslagenMedewerker.getRechten(),
                opgeslagenMedewerker.getEmailVerified());

    }

    @Transactional
    public UpdateMedewerkerDTO update(Long id, UpdateMedewerkerDTO updateMedewerkerDTO){

        Medewerker entity = findById(id);

        entity.setGebruikersnaam(updateMedewerkerDTO.getGebruikersnaam());
        entity.setVoornaam(updateMedewerkerDTO.getVoornaam());
        entity.setAchternaam(updateMedewerkerDTO.getAchternaam());
        entity.setEmail(updateMedewerkerDTO.getEmail());
        entity.setTelefoonnummer(updateMedewerkerDTO.getTelefoonnummer());
        entity.setActive(updateMedewerkerDTO.getActive());
        entity.setEmailVerified(updateMedewerkerDTO.getEmailVerified());

        return new UpdateMedewerkerDTO(entity.getGebruikersnaam(), entity.getVoornaam(), entity.getAchternaam(), entity.getEmail(),
                entity.getTelefoonnummer(), entity.getActive(), entity.getEmailVerified());
    }

    @Override
    public Medewerker findById(Long id) {
        return medewerkerRepository.getOne(id);
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
