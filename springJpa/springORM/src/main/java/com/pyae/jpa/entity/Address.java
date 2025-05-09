package com.pyae.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address {

	@Column(table = "address_tbl")
	private String building;
	@Column(table = "address_tbl")
	private String street;
	@Column(table = "address_tbl")
	private String quarter;
	@Column(table = "address_tbl")
	private String township;
	
}
