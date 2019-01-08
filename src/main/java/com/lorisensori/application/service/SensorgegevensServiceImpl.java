package com.lorisensori.application.service;

import com.lorisensori.application.DAO_interfaces.SensorgegevensRepository;
import com.lorisensori.application.domain.Sensorgegevens;
import com.lorisensori.application.domain.Tank;
import com.lorisensori.application.exceptions.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

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

	@Override
	public Set<Sensorgegevens> findByTank(Tank tank) {
		return sensorgegevensRepository.findByTank(tank);
	}
}

/*// Hier alleen alive bericht opslaan, alarmen en acks in sensorgegevenslog.
=======
package com.lorisensori.application.service;

import com.lorisensori.application.DAO_interfaces.SensorgegevensRepository;
import com.lorisensori.application.domain.Sensorgegevens;
import org.springframework.stereotype.Service;

//Business logic goes here NOT in the repository
@Service("sensorgegevensService")
public class SensorgegevensServiceImpl implements SensorgegevensService {

    //Business logic goes here NOT in the repository
	@Service("sensorgegevensService")
	public class SensorgegevensServiceImpl implements SensorgegevensService {
	
	    private final SensorgegevensRepository sensorgegevensRepository;
	
	    public SensorgegevensServiceImpl(SensorgegevensRepository sensorgegevensRepository) {
	        this.sensorgegevensRepository = sensorgegevensRepository;
	    }
	
	    @Override
	    public Sensorgegevens save(Sensorgegevens sensorgegevens) {
	        return sensorgegevensRepository.save(sensorgegevens);
	    }
	
	    @Override
	    public Iterable<Sensorgegevens> findAll() {
	        return sensorgegevensRepository.findAll();
	    }
	}

	private final SensorgegevensRepository sensorgegevensRepository;

    public SensorgegevensServiceImpl(SensorgegevensRepository sensorgegevensRepository) {
        this.sensorgegevensRepository = sensorgegevensRepository;
    }

    @Override
    public Sensorgegevens save(Sensorgegevens sensorgegevens) {
        return sensorgegevensRepository.save(sensorgegevens);
    }

    @Override
    public Iterable<Sensorgegevens> findAll() {
        return sensorgegevensRepository.findAll();
    }
}*/

