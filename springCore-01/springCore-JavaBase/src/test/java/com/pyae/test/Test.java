package com.pyae.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pyae.AnnotatedBean;
import com.pyae.ApplicationConfig;
import com.pyae.HelloBean;

public class Test {
	@org.junit.jupiter.api.Test
	void test() {
		try(var context = new AnnotationConfigApplicationContext(ApplicationConfig.class)){
			var bean = context.getBean(HelloBean.class);
			var Abean =context.getBean(AnnotatedBean.class);
			System.out.println(Abean.sayHello());
			System.out.println(bean.sayHello());
		}
	}
}
