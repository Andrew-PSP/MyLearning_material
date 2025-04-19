package com.pyae.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pyae.ApplicationConfig;
import com.pyae.MyClient;

public class TestJavaBase {

	@Test
	void test() {
		
		try(var context = new AnnotationConfigApplicationContext(ApplicationConfig.class)){
			var bean1 = context.getBean("client1",MyClient.class);
			var bean2 = context.getBean("client2",MyClient.class);
			bean1.showMessages();
			bean2.showMessages();
		}
	}
}
