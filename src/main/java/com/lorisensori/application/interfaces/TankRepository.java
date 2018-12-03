package com.lorisensori.application.interfaces;

import com.lorisensori.application.domain.Tank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TankRepository extends JpaRepository<Tank, Long> {

    boolean existsByTanknaam(String tanknaam);

    Optional<Tank> findByTanknaam(String tanknaam);
}