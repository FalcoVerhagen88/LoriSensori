package com.lorisensori.application.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lorisensori.application.domain.Recht;
import com.lorisensori.application.enums.RechtEnums;
import com.lorisensori.application.interfaces.RechtRepository;

@Service
public class RechtService {

	@Autowired
	private RechtRepository rechtRepository;

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
