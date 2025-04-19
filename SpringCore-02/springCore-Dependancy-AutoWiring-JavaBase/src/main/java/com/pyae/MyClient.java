package com.pyae;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;



public class MyClient {
	
//	@Autowired / common way
	private MailService service;
	
	
//	@Autowired(optional)
	public MyClient(MailService service) {
		super();
		this.service = service;
	}

//	@Autowired / third way
	public void setService(MailService service) {
		this.service = service;
	}
	
	public void showMessages() {
		System.out.println(service.showMessage());
	}
	


}
