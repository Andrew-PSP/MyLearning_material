package com.pyae.jpa.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SecondaryTable;
import lombok.Data;

@Entity
@Data
@SecondaryTable(name ="address_tbl")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(unique = true)
	private String phone;
	
	private LocalDate dob;
	
//	@Embedded(optional
	private Address address;
	
	@Lob
	private String remark;
}
