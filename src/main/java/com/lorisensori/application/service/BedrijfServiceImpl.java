package com.lorisensori.application.service;

import com.lorisensori.application.DAO_interfaces.BedrijfRepository;
import com.lorisensori.application.DTOs.bedrijfDTOs.BedrijfDTO;
import com.lorisensori.application.DTOs.bedrijfDTOs.UpdateBedrijfDTO;
import com.lorisensori.application.domain.Bedrijf;
import com.lorisensori.application.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

//Business logic goes here NOT in the repository
@Service("bedrijfService")
public class BedrijfServiceImpl implements BedrijfService {

    private final BedrijfRepository bedrijfRepository;
    private ModelMapper modelMapper;

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
    public List<BedrijfDTO> findAll() {
        return bedrijfRepository.findAll().stream()
                .map(entity -> new BedrijfDTO(entity.getBedrijfsnaam(), entity.getTelefoonnummer(), entity.getRekeningnummer(),
                        entity.getBtwNummer(), entity.getKvkNummer(), entity.getAdres(), entity.getContactpersoon(),
                        entity.getStatus(),entity.getMedewerkers(), entity.getTanks())).collect(Collectors.toList());
    }

    @Transactional
    public BedrijfDTO create(BedrijfDTO bedrijfDTO){

        Bedrijf nieuwBedrijf = new Bedrijf();

        nieuwBedrijf.setBedrijfsnaam(bedrijfDTO.getBedrijfsnaam());
        nieuwBedrijf.setTelefoonnummer(bedrijfDTO.getTelefoonnummer());
        nieuwBedrijf.setRekeningnummer(bedrijfDTO.getRekeningnummer());
        nieuwBedrijf.setBtwNummer(bedrijfDTO.getBtwNummer());
        nieuwBedrijf.setKvkNummer(bedrijfDTO.getKvkNummer());
        nieuwBedrijf.setAdres(bedrijfDTO.getAdres());
        nieuwBedrijf.setContactpersoon(bedrijfDTO.getContactpersoon());
        nieuwBedrijf.setStatus(bedrijfDTO.getStatus());
        nieuwBedrijf.setMedewerkers(bedrijfDTO.getMedewerkers());
        nieuwBedrijf.setTanks(bedrijfDTO.getTanks());

        Bedrijf opgeslagenBedrijf = bedrijfRepository.saveAndFlush(nieuwBedrijf);

        return modelMapper.map(opgeslagenBedrijf, BedrijfDTO.class);
    }

    @Transactional
    public UpdateBedrijfDTO update(Long id, UpdateBedrijfDTO updateBedrijfDTO){

        Bedrijf entity = findOneSafe(id);

        entity.setBedrijfsnaam(updateBedrijfDTO.getBedrijfsnaam());
        entity.setTelefoonnummer(updateBedrijfDTO.getTelefoonnummer());
        entity.setRekeningnummer(updateBedrijfDTO.getRekeningnummer());
        entity.setBtwNummer(updateBedrijfDTO.getBtwNummer());
        entity.setKvkNummer(updateBedrijfDTO.getKvkNummer());
        entity.setAdres(updateBedrijfDTO.getAdres());
        entity.setContactpersoon(updateBedrijfDTO.getContactpersoon());
        entity.setStatus(updateBedrijfDTO.getStatus());
        entity.setMedewerkers(updateBedrijfDTO.getMedewerkers());
        entity.setTanks(updateBedrijfDTO.getTanks());

        return modelMapper.map(entity, UpdateBedrijfDTO.class);
    }

    private Bedrijf findOneSafe(Long id) {
        Bedrijf bedrijf = bedrijfRepository.getOne(id);
        if (bedrijf == null) {
            throw new ResourceNotFoundException("Bedrijf", "Id", id);
        } else {
            return bedrijf;
        }
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
