package com.pyae.pos.repo;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pyae.pos.output.ProductDto;

@Repository
public class ProductRepoImpl implements ProductRepo{
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	private RowMapper<ProductDto> rowMapper = new DataClassRowMapper<>(ProductDto.class);

	@Override
	public Optional<ProductDto> findByCode(String code) {
		
		return template.query("select * from Product where code = :code", Map.of("code",code), rowMapper).stream().findAny();
	}

}
