package com.pyae;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

	/* first way =primary
	 * second way = qualifier
	 * third way = alias ( when giving alias, you can give many names and the second name can be given same in other bean and DI will use the last bean method)
	 * fourth way = same method name here and variable name from client
	 * fifth way =Only in AnnotationBase, Priority using Jakarta annotation*/
	
	@Bean(name = {
			"service1",
			"service"
	})
//	@Qualifier("q1")
//	@Bean("service1")
	MailService service10() {
		return new MailService("service1");
	}
//////////////////////////////////////
	
//	@Qualifier("q2")
	@Bean(name = {
			"service2",
			"service"
	})
	MailService service20() {
		return new MailService("service2");
	}
//////////////////////////////////////
	
//	@Primary
//	@Qualifier("q3")
	@Bean(name = {
			"service3",
			"service"
	})
	MailService service30() {
		return new MailService("service3");
	}
//////////////////////////////////////	
	@Bean
	MyClient myClient() {
		return new MyClient();
	}
}
