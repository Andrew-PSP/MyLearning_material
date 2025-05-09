package com.pyae.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity
public class Contact {

	@Id
	private int id;
	
//	@MapsId
//	@JoinColumn(unique = true)
	@PrimaryKeyJoinColumn
	@OneToOne(optional = false)
	private Merchant merchant;
	
	@Column(nullable = false)
	private String phone;
	@Column(nullable = false)
	private String email;
	
	private String building;
	private String Street;
	private String quarter;
	private String township;
}
