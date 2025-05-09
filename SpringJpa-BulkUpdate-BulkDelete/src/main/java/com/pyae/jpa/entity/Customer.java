package com.pyae.jpa.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@NamedQuery(name = "Customer.update", query = "update Customer c set c.name = :name, c.phone = :phone where c.id = :id")
@NamedQuery(name = "Customer.delete", query= "delete from Customer c where c.id = :id")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String phone;
	private String email;
	
	private LocalDate lastModifiedAt;
	
	public Customer(int id, String name, String phone, String email) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	
	//listener and callback method are not working on criteria and JPQL
	@PreUpdate
	public void updateAt() {
		this.lastModifiedAt = LocalDate.now();
	}
	
	@PreRemove
	public void removeAt() {
		System.out.printf("Remove %s.%n", name);
	}
}
