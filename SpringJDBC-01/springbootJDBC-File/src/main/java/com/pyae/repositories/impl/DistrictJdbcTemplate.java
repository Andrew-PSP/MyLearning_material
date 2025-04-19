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

import com.pyae.domain.DistrictDto;
import com.pyae.repositories.DistrictRepo;

@Repository
@Profile("jdbc")
public class DistrictJdbcTemplate implements DistrictRepo {
	@Autowired
	private JdbcTemplate template;
	private RowMapper<DistrictDto> rowMapper;
	private static final String SELECT_SQL= """
			select ds.id,ds.name, dv.id divisionId , dv.name divisionName, count(ts.id) townships from district ds
			join division dv on dv.id = ds.division_id
			left join township ts on ds.id = ts.district_id
			""";
	private static final String GROUPBY = "group by ds.id,ds.name,dv.id,dv.name";
	
	public DistrictJdbcTemplate() {
		rowMapper = new DataClassRowMapper<>(DistrictDto.class);
	}
	
	@Override
	public List<DistrictDto> search(Integer divisionId, String name) {
		var sql = new StringBuffer(SELECT_SQL).append("where 1= 1");
		var params = new ArrayList<Object>();
		
		if(null != divisionId) {
			sql.append(" and dv.id = ?");
			params.add(divisionId);
		}
		if( null !=name) {
			sql.append(" and lower(ds.name) like ?");
			params.add(name.toLowerCase().concat("%"));
		}
		
		sql.append(" ").append(GROUPBY);
		
		
		return template.query(sql.toString(), rowMapper, params.toArray());
	}
	@Override
	public Optional<DistrictDto> findById(int id) {
		return template.query(" %s where ds.id = ? %s".formatted(SELECT_SQL, GROUPBY), rowMapper, id)
				.stream().findAny();
		
	}

}
