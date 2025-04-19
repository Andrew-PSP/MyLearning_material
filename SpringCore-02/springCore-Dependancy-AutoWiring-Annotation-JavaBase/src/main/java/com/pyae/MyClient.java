package com.pyae;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MyClient {
//	@Autowired /common way
	private final MailService service;
	
	
//	@Autowired(optional=ok if write or not) /second way
//	public MyClient(MailService service) {
//		super();
//		this.service = service;
//	}
	
//	@Autowired / third way
//	public void setService(MailService service) {
//		this.service = service;
//	}


	public void showMessages() {
		System.out.println(service.showMessage());
	}
	


}
