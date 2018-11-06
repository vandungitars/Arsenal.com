package com.vandung.service;

import com.vandung.model.Admin;

public interface AdminAccountService{

	void save(Admin admin);
	Iterable<Admin> findAll();
}
