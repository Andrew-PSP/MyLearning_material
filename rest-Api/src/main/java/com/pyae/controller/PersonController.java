package com.pyae.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pyae.entity.Name;
import com.pyae.entity.Person;
import com.pyae.entity.Person1;
import com.pyae.entity.Person2;


@RestController
public class PersonController {

	
	@GetMapping("/v1/person")
	public Person UrlVersion1() {
		return new Person1("Kyaw Gyi");
	}
	
	@GetMapping("/v2/person")
	public Person UrlVersion2() {
		return new Person2(new Name("Kyaw", "Gyi"));
	}
	
	@GetMapping(path ="/person", params = "version=1")
	public Person requestParameterVersion1() {
		return new Person1("Kyaw Gyi");
	}
	
	@GetMapping(path ="/person", params = "version=2")
	public Person requestParameterVersion2() {
		return new Person2(new Name("Kyaw", "Gyi"));
	}
	
	@GetMapping(path ="/person/header", headers = "PERSON-VERSION=1")
	public Person requestHeaderVersion1() {
		return new Person1("Kyaw Gyi");
	}
	
	@GetMapping(path ="/person/header", headers = "PERSON-VERSION=2")
	public Person requestHeaderVersion2() {
		return new Person2(new Name("Kyaw", "Gyi"));
	}
	
	@GetMapping(path ="/person/accept", produces = "application/app-v1+json")
	public Person acceptHeaderVersion1() {
		return new Person1("Kyaw Gyi");
	}
	
	@GetMapping(path ="/person/accept", produces = "application/app-v2+json")
	public Person acceptHeaderVersion2() {
		return new Person2(new Name("Kyaw", "Gyi"));
	}
}
