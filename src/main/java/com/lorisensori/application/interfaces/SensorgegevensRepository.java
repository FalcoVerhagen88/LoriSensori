package com.lorisensori.application.interfaces;

import com.lorisensori.application.logic.Sensorgegevens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorgegevensRepository extends JpaRepository<Sensorgegevens, Long> {

}
