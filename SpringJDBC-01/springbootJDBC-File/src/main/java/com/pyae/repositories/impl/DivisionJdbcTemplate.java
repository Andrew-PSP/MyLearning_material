package com.pyae.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.pyae.domain.DivisionDto;
import com.pyae.repositories.DivisionRepo;

@Repository
@Profile("Jdbc")
public class DivisionJdbcTemplate implements DivisionRepo{

	@Autowired
	private JdbcTemplate template;
	private RowMapper<DivisionDto> rowMapper;
	
	private static final String SELECT_SQL="""
			select dv.id,dv.name,count(ds.id) districts from division dv 
			join district ds on dv.id=ds.division_id""";
	
	public DivisionJdbcTemplate() {
		rowMapper = new DataClassRowMapper<>(DivisionDto.class);
	}
	
	@Override
	public List<DivisionDto> search(String name) {
		var sql = new StringBuffer(SELECT_SQL);
		var params = new ArrayList<Object>();
		
		if(StringUtils.hasLength(name)) {
			sql.append(" where lower(dv.name) like ?");
			params.add(name.toLowerCase().concat("%"));
		}
		sql.append(" group by dv.name,dv.id ");
		
		return template.query(sql.toString(), rowMapper, params.toArray());
	}

	@Override
	public Optional<DivisionDto> findById(int id) {
		return template.query("%s where dv.id = ? group by dv.name,dv.id".formatted(SELECT_SQL), rowMapper, id)
				.stream().findAny();
		
	}

}
