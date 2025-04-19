package com.pyae;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

	@Bean("service")
	MailService mailService() {
		return new MailService();
	}
	
	@Bean("client1")
	MyClient myClient1() {
		var bean = new MyClient(mailService());
		bean.setName("Client 1");
		return bean;
	}
	@Bean("client2")
	MyClient myClient2() {
		var bean = new MyClient(mailService());
		bean.setName("Client 2");
		return bean;
	}
}
