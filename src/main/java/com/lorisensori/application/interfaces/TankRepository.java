package com.lorisensori.application.interfaces;


import com.lorisensori.application.DTO.TankDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TankRepository extends JpaRepository<TankDTO, Long> {

}
