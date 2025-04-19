package com.pyae.pos.repo;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.pyae.pos.input.ShoppingCart;
import com.pyae.pos.output.SaleInfo;

@Repository
public class SaleHistoryRepoImpl implements SaleHistoryRepo {

	@Value("${sql.sale.history.select}")
	private String selectSql;
	
	@Value("${sql.sale.history.insert}")
	private String insertSql;
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	private RowMapper<SaleInfo> rowMapper = new DataClassRowMapper<>(SaleInfo.class);
	
	

	@Override
	public List<SaleInfo> search(String salePerson, LocalDate from, LocalDate to) {
		var where = new StringBuffer("1=1");
		var params = new HashMap<String, Object>();
		
		if(StringUtils.hasLength(salePerson)) {
			where.append(" and sh.sale_person = :salePerson");
			params.put("salePerson",salePerson);
		}
		if( null != from) {
			where.append(" and sh.sale_at >= :saleAt");
			params.put("saleAt", Timestamp.valueOf(from.atStartOfDay()));
		}
		if( null != to) {
			where.append(" and sh.sale_at <= :saleTo");
			params.put("saleTo", Timestamp.valueOf(to.plusDays(1).atStartOfDay()));
		}

		return template.query(selectSql.formatted(where), params, rowMapper);
	}

	@Override
	public Optional<SaleInfo> findById(int id) {
		return template.query(selectSql.formatted("sh.id = :id"),Map.of("id",id) ,rowMapper).stream().findAny();
	}

	@Override
	public Integer create(ShoppingCart cart) {
		var key = new GeneratedKeyHolder();
		template.update("insert into sale_history(sale_person) values(:i)", new MapSqlParameterSource().addValue("i", cart.getSalePerson()), key, new String[]{"id"});
		
		return key.getKey().intValue();
	}

}
