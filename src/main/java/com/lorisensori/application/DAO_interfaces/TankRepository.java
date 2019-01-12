package com.lorisensori.application.DAO_interfaces;

import com.lorisensori.application.domain.Tank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TankRepository extends JpaRepository<Tank, Long> {

	Tank findByDevId(String devId);
	Tank findByTankId(Long id);
}