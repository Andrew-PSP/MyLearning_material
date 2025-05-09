package com.pyae.jpa.entity2;

import java.util.List;

import com.pyae.jpa.converter.ListToStringConverter;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Student {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String email;
	private String phone;
	
	@Convert(converter = ListToStringConverter.class)
	private List<String> interests;
	
	@AttributeOverride(name = "name", column = @Column(name = "Father_name"))
	@AttributeOverride(name = "occupation", column = @Column(name = "Father_occupation"))
	private Parent father;
	@AttributeOverride(name = "name", column = @Column(name = "Mother_name"))
	@AttributeOverride(name = "occupation", column = @Column(name = "Mother_occupation"))
	private Parent Mother;
}
