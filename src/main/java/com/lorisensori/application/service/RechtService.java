package com.lorisensori.application.service;

import com.lorisensori.application.DAO_interfaces.RechtRepository;
import com.lorisensori.application.domain.Recht;
import com.lorisensori.application.enums.RechtEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class RechtService {

	private final RechtRepository rechtRepository;

	@Autowired
	public RechtService(RechtRepository rechtRepository) {
		this.rechtRepository = rechtRepository;
	}

	/**
	 * Find role in the database by name
	 */
	public Optional<Recht> findByRecht(RechtEnums rechtNaam) {
		return rechtRepository.findByRecht(rechtNaam);
	}

	/**
	 * Find all roles from the database
	 */
	public Collection<Recht> findAll() {
		return rechtRepository.findAll();
	}

}
