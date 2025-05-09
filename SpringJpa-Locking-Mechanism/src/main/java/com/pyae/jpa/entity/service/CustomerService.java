package com.pyae.jpa.entity.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pyae.jpa.entity.Customer;
import com.pyae.jpa.entity.repo.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo repo;
	
	@Transactional
	public int insert(Customer c) {
		return repo.save(c).getId();
	}
	
	@Transactional
	public void addAmount(int id, BigDecimal amount) {
		var entity = repo.findById(id).orElseThrow();
		entity.setAmount(entity.getAmount().add(amount));
	}
	
	@Transactional
	public void substractAmount(int id, BigDecimal amount) {
		var entity = repo.findById(id).orElseThrow();
		entity.setAmount(entity.getAmount().subtract(amount));
	}
}
