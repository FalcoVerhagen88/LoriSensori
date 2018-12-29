package com.lorisensori.application.DAO_interfaces;

import com.lorisensori.application.domain.Recht;
import com.lorisensori.application.enums.RechtEnums;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RechtRepository extends JpaRepository<Recht, Long> {

	Optional<Recht> findByRecht(RechtEnums recht);
}
