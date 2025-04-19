package com.pyae.pos.service;

import com.pyae.pos.output.ProductDto;

public interface ProductService {

	public ProductDto findByCode(String code);
}
