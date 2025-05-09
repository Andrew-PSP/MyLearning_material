package com.pyae.jpa.entity;

import java.util.HashMap;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.TableGenerator;
import lombok.Data;

@Entity
@Data
@TableGenerator(name = "Product_seq", allocationSize = 1, initialValue = 1)
public class Product {

	@Id
	@GeneratedValue(generator = "Product_seq")
	private int id;
	private String name;
	private int price;
	
	@ElementCollection
//	@CollectionTable(joinColumns = @JoinColumn(name ="Product_id"))
	private List<Tag> tags;
	
	@ElementCollection
	@MapKeyColumn(name = "Tag_Languages")
	private HashMap<String, Tag> tagLanguages;
}
