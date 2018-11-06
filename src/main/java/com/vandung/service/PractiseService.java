package com.vandung.service;

import com.vandung.model.Practise;

public interface PractiseService {

	Practise findById(Long id_practise);
	void save(Practise practise);
}
