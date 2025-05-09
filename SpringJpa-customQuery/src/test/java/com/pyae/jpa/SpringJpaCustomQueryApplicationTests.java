package com.pyae.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pyae.jpa.base.dto.input.CustomerSearch;
import com.pyae.jpa.entity.Customer.Gender;
import com.pyae.jpa.service.CustomerService;

@SpringBootTest
class SpringJpaCustomQueryApplicationTests {
	
	@Autowired
	private CustomerService service;

	@Test
	void contextLoads() {
		var search = new CustomerSearch("Thidar","0979585045","Thi@gmail.com", Gender.Female);
		
		service.searchEntity(search);
		
	
		
	}

}
