package com.pyae.test;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.pyae.HelloBean;
import com.pyae.annotation.AnnotatedBean;

public class Test {
	
	@org.junit.jupiter.api.Test
	void test() {
		try(var context = new GenericXmlApplicationContext()){
			context.load("classpath:/Application.xml");
			context.refresh();
			var bean = context.getBean(HelloBean.class);
			var annotatedBean = context.getBean(AnnotatedBean.class);
			System.out.println(bean.sayHello());
			System.out.println(annotatedBean.sayHello());
		}
	}
}
