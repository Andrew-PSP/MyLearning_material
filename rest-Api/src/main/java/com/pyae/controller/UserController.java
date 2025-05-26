package com.pyae.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pyae.entity.Customer;
import com.pyae.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping(path = "/Users")
	public List<Customer> getUsers() {
		return service.findAll();
	}
	
	@GetMapping(path = "/Users/{id}")
	public Customer getUser(@PathVariable int id) {
		return service.findById(id);
	}
	
	@PostMapping(path= "/Users")
	public ResponseEntity<Customer> createUser(@RequestBody Customer user) {
		var id = service.create(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/Users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		service.deleteById(id);
	}
	
	
	
	
}
