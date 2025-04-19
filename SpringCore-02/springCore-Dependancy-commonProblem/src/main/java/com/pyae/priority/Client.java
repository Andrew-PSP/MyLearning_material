package com.pyae.priority;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Client {

	@Autowired
	private Service service;
	//using order annotation to identify the order of list(collection)
	@Autowired
	private List<Service> services;

	public void showMessages() {
		System.out.println(service.showMessage());
	}
	
	public void showListMessages() {
		services.forEach(a-> System.out.println(a.showMessage()));
	}
	
}
