package com.pyae.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.pyae.jpa.entity.Customer;
import com.pyae.jpa.repo.CustomerRepo;

@SpringBootTest
@TestMethodOrder(value = OrderAnnotation.class)
@ActiveProfiles("criteria")
@Sql(scripts = "/customers.sql", executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
class SpringJpaBulkUpdateBulkDeleteApplicationTests {
	
	@Autowired
	private CustomerRepo repo;

	@ParameterizedTest
	@Order(1)
	@CsvSource(value = {
			"1	Maung Aung	0911112222	aung@gmail.com",
			"2	Maung Maung	0911112223	maung@gmail.com",
			"3	Thidar	0911112224	thidar@gmail.com",
			"4	Nilar	0911112225	nilar@gmail.com",
		}, delimiter = '\t')
	void findById_test(@AggregateWith(ArguementAggregatorCustomer.class) Customer c) {
		var entity = repo.findById(c.getId());
		assertTrue(entity.isPresent());
		
		entity.ifPresent( e -> {
			assertEquals(e.getId(), c.getId());
			assertEquals(e.getName(), c.getName());
			assertEquals(e.getEmail(), c.getEmail());
			assertEquals(e.getPhone(), c.getPhone());
		});
	}
	
	@ParameterizedTest
	@MethodSource
	@Order(2)
	void update_test(int id, String name, String phone) {
		var result = repo.update(id, name, phone);
		assertEquals(result, 1);
	}
	
	static Stream<Arguments> update_test(){
		return Stream.of(
				Arguments.of(1, "Aung Aung", "0911112223"),
				Arguments.of(2,"Maung Maung Naing", "0911112224"),
				Arguments.of(3,"Thidar Aung", "0911112225"),
				Arguments.of(4, "Nilar Oo", "0911112226")
				);
	}
	
	
	@ValueSource(ints = {1,2,3,4})
	@Order(3)
	void delete_test(int id) {
		var result = repo.delete(id);
		assertEquals(result, 1);
	}
	

}
