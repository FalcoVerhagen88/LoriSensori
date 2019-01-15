package com.lorisensori.application.DAO_interfaces;

import com.lorisensori.application.domain.Sensorgegevens;
import com.lorisensori.application.domain.Tank;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorgegevensRepository extends JpaRepository<Sensorgegevens, Long> {

    Set<Sensorgegevens> findByTank(Tank tank);
}
