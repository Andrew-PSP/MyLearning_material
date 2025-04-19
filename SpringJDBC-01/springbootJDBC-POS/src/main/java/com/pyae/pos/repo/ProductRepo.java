package com.pyae.pos.repo;

import java.util.Optional;

import com.pyae.pos.output.ProductDto;

public interface ProductRepo {
	
	Optional<ProductDto> findByCode(String code);

}
