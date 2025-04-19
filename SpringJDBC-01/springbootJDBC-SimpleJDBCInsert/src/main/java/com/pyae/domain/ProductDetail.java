package com.pyae.domain;

import java.util.Objects;

public class ProductDetail {
	int id; 
	String name; 
	String category;
	String image; 
	int price;
	public ProductDetail(int id, String name, String category, String image, int price) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.image = image;
		this.price = price;
	}
	@Override
	public int hashCode() {
		return Objects.hash(category, id, image, name, price);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDetail other = (ProductDetail) obj;
		return Objects.equals(category, other.category) && id == other.id && Objects.equals(image, other.image)
				&& Objects.equals(name, other.name) && price == other.price;
	}
	
	
}
