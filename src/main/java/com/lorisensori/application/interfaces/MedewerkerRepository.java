package com.lorisensori.application.interfaces;

import com.lorisensori.application.logic.Bedrijf;
import com.lorisensori.application.logic.Medewerker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedewerkerRepository extends JpaRepository<Medewerker, String> {
    boolean existsByBedrijf_Bedrijfsnaam(String bedrijfsnaam);
}
