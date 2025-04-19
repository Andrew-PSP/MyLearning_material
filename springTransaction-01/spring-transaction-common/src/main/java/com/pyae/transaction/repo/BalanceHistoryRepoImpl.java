package com.pyae.transaction.repo;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.SimplePropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.pyae.transaction.dto.BalanceHistoryForm;

@Repository
public class BalanceHistoryRepoImpl implements BalanceHistoryRepo {
	
	private SimpleJdbcInsert insert;
	
	public BalanceHistoryRepoImpl(DataSource dataSource) {
		insert = new SimpleJdbcInsert(dataSource).withTableName("BALANCE_HISTORY");
						
	}

	@Override
	public void create(BalanceHistoryForm input) {
		insert.execute(new SimplePropertySqlParameterSource(input));
		
	}

}
