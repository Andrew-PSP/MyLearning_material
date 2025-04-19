package com.pyae.dao;

import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.pyae.dto.AccountDto;
import com.pyae.dto.AccountForm;
//@Repository
public class AccountDaoImpl implements AccountDao{

	private JdbcTemplate template;
	private RowMapper<AccountDto> rowMapper;
	
	public AccountDaoImpl(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
		rowMapper = ((rs,roNum) -> new AccountDto(rs.getInt("id"), rs.getString("name"), rs.getString("phone")));
//		rowMapper = new DataClassRowMapper<>(AccountDto.class);
	}
	
	@Override
	public int create(AccountForm form) {
		var keys = new GeneratedKeyHolder();
		template.update(con->{
			var stmt = con.prepareStatement("insert into account(name,phone) values (?,?);",Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, form.name());
			stmt.setString(2, form.phone());
			return stmt;
		}, keys);
		return keys.getKey().intValue();
	}

	@Override
	public long count() {
		return template.queryForObject("select count(id) from account", long.class);
	}

	@Override
	public AccountDto findById(int id) {
		var list = template.query("select * from account where id= ?", rowMapper, id);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}

}
