package com.pyae.bean;

import org.springframework.stereotype.Component;

@Component
public class HelloBean {

	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
