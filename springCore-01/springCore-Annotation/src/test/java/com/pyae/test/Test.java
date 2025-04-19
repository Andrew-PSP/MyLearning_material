package com.pyae.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pyae.HelloBean;

public class Test {
	@org.junit.jupiter.api.Test
	void test() {
		
		try(var context = new AnnotationConfigApplicationContext("com.pyae")){
			var bean = context.getBean(HelloBean.class);
			System.out.println(bean.sayHello());
		}
	}
}
