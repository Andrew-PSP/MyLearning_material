package com.pyae.circularDependancy;

import org.springframework.stereotype.Component;

@Component
public class BeanB {
	
	
	private BeanC bean;

	public BeanB(BeanC bean) {
		super();
		this.bean = bean;
	}

	protected String sayHello() {
		return "Hello from bean A";
	}
	
	public void showMessage() {
		System.out.println(bean.sayHello());
	}


	
}
