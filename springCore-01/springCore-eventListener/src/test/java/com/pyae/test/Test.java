package com.pyae.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pyae.ApplicationConfig;
import com.pyae.event.MyEvent;
import com.pyae.event.publisher.MyEventPublisher;

public class Test {

	@org.junit.jupiter.api.Test
	void test() {
		try(var context = new AnnotationConfigApplicationContext(ApplicationConfig.class)){
			
			var publisher = context.getBean(MyEventPublisher.class);
			publisher.publish(new MyEvent("First Message"));
			publisher.publish(new MyEvent("Second Message"));
		}
	}
}
