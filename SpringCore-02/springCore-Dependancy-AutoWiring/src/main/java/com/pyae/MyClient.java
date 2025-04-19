package com.pyae;

public class MyClient {
	
	private MailService service;
	
	

	public MyClient() {
		super();
	}

	/*
	 * auto-wiring by using argument constructor
	 * In xml, auto-wire = constructor
	 */	
	public MyClient(MailService service) {
		super();
		this.service = service;
	}
	
	/* auto-wiring by using setter */
	public void setService(MailService service) {
		this.service = service;
	}

	public MailService getService() {
		return service;
	}


	public void showMessages() {
		System.out.printf(service.showMessage());
	}
	


}
