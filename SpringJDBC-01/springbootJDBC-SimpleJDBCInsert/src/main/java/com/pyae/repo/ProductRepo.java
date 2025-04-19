package com.pyae.repo;

import com.pyae.domain.ProductDetail;
import com.pyae.domain.ProductForm;

public interface ProductRepo {

	public int insertProduct(ProductForm form);
	
	public ProductDetail findById(int id);
}
