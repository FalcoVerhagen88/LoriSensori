package com.lorisensori.application.interfaces;

import com.lorisensori.application.DTO.BedrijfDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BedrijfRepository extends JpaRepository<BedrijfDTO, String> {
}
