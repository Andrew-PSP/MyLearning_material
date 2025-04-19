package com.pyae.priority;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.annotation.Priority;

@Component
@Priority(1)
@Order(3)
public class Service1 implements Service {

private String name;
	

	public Service1() {
		super();
		this.name = "Service1";
	}

	@Override
	public String showMessage() {
		return "Hello from %s".formatted(name);
	}
}
