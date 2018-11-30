package com.lorisensori.application.interfaces;

import com.lorisensori.application.logic.Bedrijf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BedrijfRepository extends JpaRepository<Bedrijf, String> {

}
