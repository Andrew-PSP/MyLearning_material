package com.pyae.jpa.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;


@Entity
@Data
//creating indexes
//@Table(indexes = {
//		@Index(columnList = "name"),
//		@Index(columnList = "shopName")
//})
public class Merchant {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String name;
	private String position;
	private String shopName;
	
	@ManyToMany(mappedBy = "merchants")
	private List<Product> products;

	
	
	
}
