package com.pyae.jpa;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pyae.jpa.entity.service.CustomerService;

@SpringBootTest
public class DistractedAction {
	
	@Autowired
	private CustomerService service;

	@Test
	void interruptedAction() {
		
		service.substractAmount(1, BigDecimal.valueOf(30_000));
	}
}
