package com.pyae;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pyae.entity.Customer;
import com.pyae.exception.ValidationUserException;
import com.pyae.service.UserService;

@SpringBootTest
class RestApiApplicationTests {

	@Autowired
	private UserService service;
	
	@Test
	void contextLoads() {
		var customer = new Customer("hello", LocalDate.of(2030, 5, 12),"hello","great");
		assertThrows(ValidationUserException.class, ()-> service.create(customer));
	}

}
