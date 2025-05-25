package com.pyae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pyae.annotation.ValidateUser;
import com.pyae.entity.Customer;
import com.pyae.exception.UserNotFoundException;
import com.pyae.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;
	
	@Transactional(readOnly = true)
	public List<Customer> findAll() {
		return repo.findAll();
	}
	
	@Transactional
	@ValidateUser
	public int create(Customer customer) {
		return repo.save(customer).getId();
	}
	
	@Transactional(readOnly = true)
	public Customer findById(int id) {
		return repo.findById(id).orElseThrow(() ->new UserNotFoundException("Please Enter User's ID correctly"));
	}
	
	@Transactional
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
}
