package com.lorisensori.application.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lorisensori.application.domain.Recht;
import com.lorisensori.application.enums.RechtEnums;


public interface RechtRepository extends JpaRepository<Recht, Long> {

	Optional<Recht> findByRecht(RechtEnums recht);
}
