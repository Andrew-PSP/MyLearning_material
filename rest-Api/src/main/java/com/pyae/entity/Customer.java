package com.pyae.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Customer  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
//	@Size(min =2, message = "Name should not be blank")
	private String name;
//	@Past(message = "Birth Date Should be in the past")
	private LocalDate dob;
	public Customer(String name, LocalDate dob) {
		super();
		this.name = name;
		this.dob = dob;
	}
	
	
	
}
