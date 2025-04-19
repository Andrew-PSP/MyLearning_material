package com.pyae.priority;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.annotation.Priority;

@Component
@Priority(3)
@Order(1)
public class Service3 implements Service{

private String name;
	

	public Service3() {
		super();
		this.name = "Service3";
	}

	public String showMessage() {
		return "Hello from %s".formatted(name);
	}
}
