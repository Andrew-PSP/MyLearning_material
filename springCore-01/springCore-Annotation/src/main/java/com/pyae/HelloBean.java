package com.pyae;

import org.springframework.stereotype.Component;

@Component
public class HelloBean {
	
	public String sayHello() {
		return "Hello from annotation Config";
	}
}
