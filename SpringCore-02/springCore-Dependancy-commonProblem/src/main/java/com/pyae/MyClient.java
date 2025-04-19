package com.pyae;

import org.springframework.beans.factory.annotation.Autowired;



public class MyClient {
	
	@Autowired
//	@Qualifier("q1")
	private MailService service;

	public void showMessages() {
		System.out.println(service.showMessage());
	}
	


}
