package com.vandung.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vandung.model.Customer;
import com.vandung.repository.CustomerRepository;

@Service("customerService")
@Transactional
public class CustomerServiceImp implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public void saveCustomer(Customer customer) {
		customerRepository.save(customer);
	}

}
