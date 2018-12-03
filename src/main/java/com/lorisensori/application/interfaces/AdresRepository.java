package com.lorisensori.application.interfaces;

import com.lorisensori.application.domain.Adres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresRepository extends JpaRepository<Adres, Long> {

}
