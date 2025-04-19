package com.pyae.test;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.pyae.MyClient;

public class Test {

	@org.junit.jupiter.api.Test
	void test() {
		
		try(var context =new GenericXmlApplicationContext("classpath:\\application.xml")){
			var bean = context.getBean("client1",MyClient.class);
			bean.showMessages();
			var bean1 = context.getBean("client2", MyClient.class);
			bean1.showMessages();
		}
	}
}
