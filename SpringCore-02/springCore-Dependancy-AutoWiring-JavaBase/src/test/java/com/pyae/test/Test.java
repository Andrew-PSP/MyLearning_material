package com.pyae.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pyae.MyClient;
import com.pyae.MyConfiguration;

public class Test {

	@org.junit.jupiter.api.Test
	void test() {
		
		try(var context = new AnnotationConfigApplicationContext(MyConfiguration.class)){
			var bean = context.getBean(MyClient.class);
			bean.showMessages();
		}
	}
}
