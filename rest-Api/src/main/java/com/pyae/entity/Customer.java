package com.pyae.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
//@JsonIgnoreProperties("password")
//*for dynamic filter
@JsonFilter("userOverview")
public class Customer  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
//	@Size(min =2, message = "Name should not be blank")
	@JsonProperty("User Name")
	@Column(nullable = false)
	private String name;
	
//	@Past(message = "Birth Date Should be in the past")
	@Column(nullable = false)
	private LocalDate dob;
	
	//static filter
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(nullable = false)
	private String password;
	
	private String details;
	
	public Customer(String name, LocalDate dob, String password, String details) {
		super();
		this.name = name;
		this.dob = dob;
		this.password = password;
		this.details = details;
	}
	
	
	
}
