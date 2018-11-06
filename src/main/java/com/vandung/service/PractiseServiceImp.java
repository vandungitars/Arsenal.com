package com.vandung.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vandung.model.Practise;
import com.vandung.repository.PractiseRepository;

@Service("PractiseService")
@Transactional
public class PractiseServiceImp implements PractiseService{

	@Autowired
	private PractiseRepository practiseRepository;
	
	@Override
	public Practise findById(Long id_practise) {
		return practiseRepository.getOne(id_practise);
	}

	@Override
	public void save(Practise practise) {
		practiseRepository.save(practise);
	}

}
