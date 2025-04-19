package com.pyae;

import org.springframework.stereotype.Component;

@Component
public class MailService {
	
	private String name;
	

	public MailService(String name) {
		super();
		this.name = name;
	}

	public String showMessage() {
		return "Hello from %s".formatted(name);
	}
}
