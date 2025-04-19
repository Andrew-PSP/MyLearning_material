package com.pyae;

public class MyClient {
	private MailService service;
	private String name;
	
	//remember to generate default constructor method if you set with setterMethod
	public MyClient() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MyClient(MailService service) {
		super();
		this.service = service;
	}

	public MailService getService() {
		return service;
	}

	public void setService(MailService service) {
		this.service = service;
	}
	
	public void showMessages() {
		System.out.printf("%s -> %s\n",name,service.showMessage());
	}
	


}
