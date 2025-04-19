package com.pyae.pos.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pyae.pos.input.SaleItem;
import com.pyae.pos.output.SaleDetailsItem;

@Repository
public class SaleProductRepoImpl implements SaleProductRepo{

	@Value("${sql.sale.product.select}")
	private String selectSql;
	@Value("${sql.sale.product.insert}")
	private String insertSql;
	
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	private RowMapper<SaleDetailsItem> rowMapper = new DataClassRowMapper<>(SaleDetailsItem.class);
	
	@Override
	public void create(int id, SaleItem item) {
		item.setSaleId(id);
		template.update(insertSql, new BeanPropertySqlParameterSource(item));
	}

	@Override
	public List<SaleDetailsItem> findBySaleHistoryId(int SaleHistoryId) {
		return template.query(selectSql, new MapSqlParameterSource("saleId", SaleHistoryId), rowMapper );
	}

}
