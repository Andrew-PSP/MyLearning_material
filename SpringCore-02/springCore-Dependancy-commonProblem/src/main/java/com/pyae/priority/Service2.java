package com.pyae.priority;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.annotation.Priority;

@Component
@Priority(2)
@Order(2)
public class Service2 implements Service{
	
private String name;
	

	public Service2() {
		super();
		this.name = "Service2";
	}

	public String showMessage() {
		return "Hello from %s".formatted(name);
	}
}
