package com.pyae.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pyae.pos.exception.PosBusinessException;
import com.pyae.pos.output.ProductDto;
import com.pyae.pos.repo.ProductRepo;

import ch.qos.logback.core.util.StringUtil;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo repo;
	
	@Override
	public ProductDto findByCode(String code) {
		if(!StringUtils.hasLength(code)) {
			throw new PosBusinessException("Please enter product code");
		}
		return repo.findByCode(code).orElseThrow(() ->new PosBusinessException("Please enter Valid code"));
	}

}
