package com.pyae.jpa;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pyae.jpa.entity.Customer;
import com.pyae.jpa.entity.service.CustomerService;

@SpringBootTest
class SpringJpaLockingMechanismApplicationTests {

	@Autowired
	private CustomerService service;
	
	@Test
	void contextLoads() {
		
//		var customer = new Customer();
//		customer.setName("Pyae");
//		customer.setPhone("09795850454");
//		customer.setAmount(BigDecimal.valueOf(100_000));
//		service.insert(customer);
		
		service.addAmount(1, BigDecimal.valueOf(50_000));
		
	}

}
