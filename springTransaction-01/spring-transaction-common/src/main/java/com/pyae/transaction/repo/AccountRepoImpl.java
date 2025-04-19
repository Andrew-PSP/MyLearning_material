package com.pyae.transaction.repo;

import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SimplePropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import com.pyae.transaction.dto.AccountInfo;
import com.pyae.transaction.dto.AmountUpdateForm;

@Repository
public class AccountRepoImpl implements AccountRepo{
	
	private static final String SELECT_SQL = "select * from ACCOUNT where account_num = :accountNum";
	private static final String UPDATE_SQL = "update ACCOUNT set amount = :amount, version = :version where account_num = :accountNum";
	
	private NamedParameterJdbcTemplate template;
	private RowMapper<AccountInfo> rowMapper;
	
	public AccountRepoImpl(DataSource dataSource) {
		template = new NamedParameterJdbcTemplate(dataSource);
		rowMapper = new DataClassRowMapper<>(AccountInfo.class);
	}
	
	@Override
	public Optional<AccountInfo> findByAccountId(String id) {
		return template.query(SELECT_SQL, Map.of("accountNum",id), rowMapper).stream().findAny();
	}

	@Override
	public void updateAmount(AmountUpdateForm input) {
		template.update(UPDATE_SQL, new SimplePropertySqlParameterSource(input));
		
	}

}
