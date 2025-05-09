package com.pyae.jpa.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LambdaTest {

	@Test
	void test() {
		var list =List.of(new Person("Pyae", "PEmail.com"),new Person("Kyaw", "KEmail.com"),new Person("Aung", "AEmail.com"));
		
		list.stream().map(Person::getName).forEach(System.out::println);
	}
}
