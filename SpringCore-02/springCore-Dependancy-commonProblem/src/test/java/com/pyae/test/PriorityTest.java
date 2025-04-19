package com.pyae.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pyae.priority.Client;

public class PriorityTest {

	@Test
	void test() {
		try(var context = new AnnotationConfigApplicationContext("com.pyae.priority")){
			var bean = context.getBean(Client.class);
			bean.showMessages();
			bean.showListMessages();
		}
	}
}
