package com.pyae.transaction.repo;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SimplePropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.pyae.transaction.dto.TransferForm;

@Repository
public class TransferRepoImpl implements TransferRepo{
	
	private static final String UPDATE_SQL = "update transfer set status = :status where id= :trxId";

	private NamedParameterJdbcTemplate template;
	private SimpleJdbcInsert insert;
	
	public TransferRepoImpl(DataSource dataSource) {
		template = new NamedParameterJdbcTemplate(dataSource);
		insert = new SimpleJdbcInsert(dataSource).withTableName("transfer")
				.usingColumns("account_from","account_to","amount","remark")
				.usingGeneratedKeyColumns("id");
	}
	
	@Override
	public int initiate(TransferForm input) {
		return insert.execute(new SimplePropertySqlParameterSource(input));
	}

	@Override
	public void updateStatus(int trxId, String status) {
		template.update(UPDATE_SQL,
				new MapSqlParameterSource().addValue("status", status).addValue("trxId", trxId));
		
	}

}
