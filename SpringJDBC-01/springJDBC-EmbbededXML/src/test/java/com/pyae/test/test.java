package com.pyae.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.pyae.dao.AccountDao;
import com.pyae.dao.AccountDaoImpl;
import com.pyae.dto.AccountForm;

@TestMethodOrder(value = OrderAnnotation.class)
public class test {

	static GenericXmlApplicationContext context;
	private AccountDao dao;
	
	@BeforeAll
	static void beforeAll() {
		context = new GenericXmlApplicationContext("classpath:/Application.xml");
		
	}
	
	@AfterAll
	static void afterAll() {
		if( null != context) {
			context.close();
		}
	}
	
	@BeforeEach
	void beforeEach() {
		dao = context.getBean("dao", AccountDaoImpl.class);
	}
	
	@ParameterizedTest
	@Order(1)
	@CsvSource({
		"thida,0977771111,1",
		"pyae,0944442221,2",
		"jonh,0955552222,3"
	})
	void insert(String name, String phone,int  expectedId) {
		var id = dao.create(new AccountForm(name, phone));
		assertEquals(expectedId,  id);	
	}
	
	@Order(2)
	@Test
	void count() {
		var id = dao.count();
		assertEquals(3, id);
	}
	
	@Order(3)
	@ParameterizedTest
	@CsvSource({
		"thida,0977771111,1",
		"pyae,0944442221,2",
		"jonh,0955552222,3"
	})
	void findById(String name, String phone, int id) {
		var accountDto = dao.findById(id);
		assertEquals(name, accountDto.name());
		assertEquals(phone, accountDto.phone());
	}
	
	@Order(3)
	@ParameterizedTest
	@ValueSource(ints = {0,4,5} )
	void findByIdNotFound(int id) {
		var accountDto = dao.findById(id);
		assertNull(accountDto);
	}
}
