package com.pyae.repo;

import javax.sql.DataSource;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SimplePropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.pyae.domain.ProductDetail;
import com.pyae.domain.ProductForm;

@Repository
public class ProductRepoSimpleJdbcInsert implements ProductRepo {


	private JdbcTemplate template;
	private RowMapper<ProductDetail> rowMapper;
	private SimpleJdbcInsert insert;
	
	public ProductRepoSimpleJdbcInsert(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
		rowMapper = new DataClassRowMapper<>(ProductDetail.class);
		insert = new SimpleJdbcInsert(dataSource).withTableName("product").usingGeneratedKeyColumns("id")
				.usingColumns("name","Category","image","price");
	}
	
	@Override
	public int insertProduct(ProductForm form) {
		
		return insert.executeAndReturnKey(new SimplePropertySqlParameterSource(form)).intValue();
	}

	@Override
	public ProductDetail findById(int id) {
		return template.queryForObject("select * from product where id = ?", rowMapper, id);
	}

}
