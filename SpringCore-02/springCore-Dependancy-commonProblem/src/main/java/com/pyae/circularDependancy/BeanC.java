package com.pyae.circularDependancy;

import org.springframework.stereotype.Component;

@Component
public class BeanC {

	private BeanA bean;

	public BeanC(BeanA bean) {
		super();
		this.bean = bean;
	}

	protected String sayHello() {
		return "Hello from Bean B";
	}
	
	public void ShowMessage() {
		System.out.println(bean.sayHello());
	}
	
}
