package com.lorisensori.application.interfaces;

import com.lorisensori.application.DTO.MedewerkerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedewerkerRepository extends JpaRepository<MedewerkerDTO, String> {
}
