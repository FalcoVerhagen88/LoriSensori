package com.lorisensori.application.service;

import com.lorisensori.application.DAO_interfaces.SensorgegevensRepository;
import com.lorisensori.application.domain.Sensorgegevens;
import com.lorisensori.application.exceptions.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("SensorgegevensService")
public class SensorgegevensServiceImpl implements SensorgegevensService{

	private final SensorgegevensRepository sensorgegevensRepository;
	
	@Autowired
	public SensorgegevensServiceImpl(SensorgegevensRepository sensorgegevensRepository) {
		
		this.sensorgegevensRepository = sensorgegevensRepository;
	}
	
	@Override
	public Sensorgegevens save(Sensorgegevens sensorgegevens) {
		
		return sensorgegevensRepository.save(sensorgegevens);
	}
	
	 public Iterable<Sensorgegevens> findAll()
	 {
		//Integer.valueOf(sensorgegevensRepository.);
		 return sensorgegevensRepository.findAll();
	 }
}

// Hier alleen alive bericht opslaan, alarmen en acks in sensorgegevenslog.
