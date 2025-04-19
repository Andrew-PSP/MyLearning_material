package com.pyae.test;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.pyae.MyClient;

public class Test {

	@org.junit.jupiter.api.Test
	void test() {
		
		try(var context = new GenericXmlApplicationContext("classpath:/Application.xml")){
			var bean = context.getBean("client1",MyClient.class);
			bean.showMessages();
			var bean2 = context.getBean("client",MyClient.class);
			System.out.println("\n"+bean+ "\n"+ bean2);
			System.out.println("\n"+bean.getService()+ "\n"+ bean2.getService());
			
		}
	}
}
