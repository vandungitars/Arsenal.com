package com.vandung.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vandung.model.Admin;
import com.vandung.repository.AdminAccountRepository;

@Service("AdminAccountService")
@Transactional
public class AdminAccountServiceImp implements AdminAccountService{

	@Autowired
	private AdminAccountRepository adminRepository;
	
	@Override
	public void save(Admin admin) {
		adminRepository.save(admin);
	}

	@Override
	public Iterable<Admin> findAll() {
		adminRepository.findAll();
		return adminRepository.findAll();
	}

	
}
