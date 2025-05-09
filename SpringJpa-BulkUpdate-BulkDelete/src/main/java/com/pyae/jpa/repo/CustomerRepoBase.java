package com.pyae.jpa.repo;

import java.util.Optional;

import com.pyae.jpa.entity.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public abstract class CustomerRepoBase implements CustomerRepo {

	@PersistenceContext
	protected EntityManager em;
	
	@Override
	public Optional<Customer> findById(int id) {
		return Optional.ofNullable(em.find(Customer.class, id));
	}
}
