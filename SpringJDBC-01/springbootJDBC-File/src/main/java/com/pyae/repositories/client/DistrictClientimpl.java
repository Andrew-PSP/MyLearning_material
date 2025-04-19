package com.pyae.repositories.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.pyae.domain.DistrictDto;
import com.pyae.repositories.DistrictRepo;

@Repository
@Profile("client")
public class DistrictClientimpl implements DistrictRepo {
	@Autowired
	private JdbcClient client;
	private RowMapper<DistrictDto> rowMapper = new DataClassRowMapper<>(DistrictDto.class);
	
	private static final String SELECT_SQL= """
			select ds.id,ds.name,dv.id divisionId ,dv.name divisionName, count(ts.id) townships from district ds 
			join division dv on dv.id = ds.division_id
			join township ts on ts.district_id = ds.id""";
	private static final String GROUPBY="group by ds.id,ds.name,dv.id,dv.name";
	@Override
	public List<DistrictDto> search(Integer divisionId, String name) {
		var sql = new StringBuffer(SELECT_SQL).append(" Where 1=1");
		var params = new ArrayList<Object>();
		if(null != divisionId) {
			sql.append(" and dv.id = ?");
			params.add(divisionId);
		}
		
		if(StringUtils.hasLength(name)) {
			sql.append(" and lower(ds.name) like ?");
			params.add(name.toLowerCase().concat("%"));
		}
		
		sql.append(" ").append(GROUPBY);
		
		return client.sql(sql.toString()).params(params).query(rowMapper).list();
	}

	@Override
	public Optional<DistrictDto> findById(int id) {
		return client.sql("%s where ds.id = :id %s".formatted(SELECT_SQL,GROUPBY)).param("id", id).query(rowMapper).optional();
	}

}
