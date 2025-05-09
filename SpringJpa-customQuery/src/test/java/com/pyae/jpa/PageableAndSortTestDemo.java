package com.pyae.jpa;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.pyae.jpa.entity.Account;
import com.pyae.jpa.entity.Customer;
import com.pyae.jpa.repo.CustomerRepo;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class PageableAndSortTestDemo {

	@Autowired
	private CustomerRepo repo;
	
	@Test
	@Order(1)
	void Sortdemo1() {
		
//		var sort = Sort.by(Sort.Direction.DESC,"name","Account.activatedAt");
		var sort = Sort.by(Sort.Order.desc("Account.activatedAt"), Sort.Order.by("name"));
		
		var result = repo.findAll(sort);
		
		System.out.println(result);
	}
	
	@Test
	@Order(2)
	void Sortdemo2TypeSort() {
		var typeSort = Sort.sort(Customer.class);
		var nameSort = typeSort.by(Customer::getName).descending();
		var activateAtSort = typeSort.by(Customer::getAccount).by(Account::getActivatedAt).ascending();
		
		var result =repo.findAll(nameSort.and(activateAtSort));
		System.out.println(result);
	}
	
	@Test
	@Order(3)
	void PageableDemo() {
		var pageable = PageRequest.of(0, 10);
		
		var result = repo.findAll(pageable);
		
		System.out.println(result);
	}
}
