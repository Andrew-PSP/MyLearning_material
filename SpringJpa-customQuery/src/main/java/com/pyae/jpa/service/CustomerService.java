package com.pyae.jpa.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pyae.jpa.base.dto.input.CustomerSearch;
import com.pyae.jpa.entity.Customer;
import com.pyae.jpa.repo.CustomerRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo repo;
	
	@Transactional(readOnly = true)
	public List<Customer> searchEntity(CustomerSearch search){
		
		Function<CriteriaBuilder, CriteriaQuery<Customer>> queryFunc = cb ->{
			CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
			var root = cq.from(Customer.class);

			cq.select(root);
			cq.where(search.where(cb, root));
			
			return cq;
		};
		
		return repo.searchEntity(queryFunc);
	}
}
