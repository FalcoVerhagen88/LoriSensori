package com.lorisensori.application.DAO_interfaces;

import com.lorisensori.application.domain.Bedrijf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedrijfRepository extends JpaRepository<Bedrijf, Long> {

    @Override
    Bedrijf getOne(Long id);

    Bedrijf findByBedrijfsnaam(String bedrijfsnaam);
}
