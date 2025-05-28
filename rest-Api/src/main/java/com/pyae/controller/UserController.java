package com.pyae.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.pyae.entity.Customer;
import com.pyae.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping(path = "/Users")
	public MappingJacksonValue getUsers() {
		var list = service.findAll();
		
		//dynamic filters part
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("User Name","dob");
		FilterProvider filters = new SimpleFilterProvider().addFilter("userOverview", filter);
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
	}
	
	@GetMapping(path = "/Users/{id}")
	public EntityModel<Customer> getUser(@PathVariable int id) {
		//adding links part
		var user = service.findById(id);
		var entityModel = EntityModel.of(user);
		var link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
		
	}
	
	@PostMapping(path= "/Users")
	public ResponseEntity<Customer> createUser(@RequestBody Customer user) {
		var id = service.create(user);
		//redirect
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		//change 200 to 201
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/Users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		service.deleteById(id);
	}
	
	
	
	
	
}
