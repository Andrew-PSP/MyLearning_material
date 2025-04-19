package com.pyae.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pyae.circularDependancy.BeanA;

public class CircularDependancyTest {

	@Test
	void test() {
		
		try(var context = new AnnotationConfigApplicationContext("com.pyae.circularDependancy")){
			var bean = context.getBean(BeanA.class);
			bean.showMessage();
		}
	}
}
