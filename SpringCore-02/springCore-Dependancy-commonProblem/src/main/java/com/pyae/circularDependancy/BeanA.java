package com.pyae.circularDependancy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanA {

	// you cannot generate constructor method here when BeanA is a dependency of BeanC. It can lead to circular dependency.
	@Autowired
	private BeanB bean;

	public void showMessage() {
		System.out.println(bean.sayHello()); 
	}

	protected String sayHello() {
		return "Hello from Bean C";
	}
}
