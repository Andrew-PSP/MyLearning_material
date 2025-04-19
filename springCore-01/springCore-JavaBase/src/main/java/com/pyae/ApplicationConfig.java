package com.pyae;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.pyae")
public class ApplicationConfig {
	
	@Bean
	HelloBean helloBean() {
		return new HelloBean();
	}
}
