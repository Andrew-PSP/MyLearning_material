package com.pyae.jpa.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
//Joined strategy = the main table is product and weight and count product are connected with productId
@Inheritance(strategy = InheritanceType.JOINED)
//Table Per Class strategy = cannot give identity, must use sequence or table generator
//create 2 inherited table(weight and count) and all the field from product are included each table. These two doesn't have a relationship.
public abstract class Product extends SecurityInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	private String name;
	
	@ManyToOne(optional = false)
//	@JoinColumn(optional)
	private Category category;
	
	private int unitPrice;
	
	@ManyToMany
	private List<Merchant> merchants;
}
