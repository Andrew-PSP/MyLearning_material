package com.pyae.jpa.repo;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.pyae.jpa.entity.Customer;

public interface CustomerRepo {

	@Transactional(readOnly = true)
	Optional<Customer> findById(int id);
	
	@Transactional
	int update(int id, String name, String phone);
	
	@Transactional
	int delete(int id);
}
