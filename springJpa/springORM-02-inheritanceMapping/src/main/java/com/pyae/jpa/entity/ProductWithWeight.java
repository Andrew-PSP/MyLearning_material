package com.pyae.jpa.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class ProductWithWeight extends Product {

	private Weight weight;
	
	public enum Weight{
		Gram,Kg
	}
}
