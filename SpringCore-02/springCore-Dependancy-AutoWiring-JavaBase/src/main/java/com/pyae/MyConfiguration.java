package com.pyae;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

	@Bean
	MailService service() {
		return new MailService();
	}
	
	@Bean
	MyClient myClient(@Autowired(required = false) MailService service) {
		return new MyClient(service);
	}
}
