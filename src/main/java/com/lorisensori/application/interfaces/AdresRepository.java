package com.lorisensori.application.interfaces;

import com.lorisensori.application.logic.Adres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresRepository extends JpaRepository<Adres, Long> {

}
