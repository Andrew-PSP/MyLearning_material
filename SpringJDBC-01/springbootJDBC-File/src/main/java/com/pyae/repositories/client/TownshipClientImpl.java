package com.pyae.repositories.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.pyae.domain.TownshipDto;
import com.pyae.repositories.TownshipRepo;

@Repository
@Profile("client")
public class TownshipClientImpl implements TownshipRepo {
	@Autowired
	private JdbcClient client;
	private RowMapper<TownshipDto> rowMapper = new DataClassRowMapper<>(TownshipDto.class);
	
	@Value("${app.sql.township-select}")
	private String selectSql;
	
	
	@Override
	public List<TownshipDto> search(Integer divisionId, Integer districtId, String name) {
		var sql = new StringBuffer(selectSql).append(" where 1=1");
		var params = new HashMap<String, Object>();
		
		if(null != divisionId) {
			sql.append(" and dv.id = :divisionId");
			params.put("divisionId", divisionId);
		}
		if(null !=districtId) {
			sql.append(" and ds.id = :districtId");
			params.put("districtId", districtId);
		}
		if( StringUtils.hasLength(name)) {
			sql.append(" and lower(ts.name) like :name");
			params.put("name", name.toLowerCase().concat("%"));
		}
		
		return client.sql(sql.toString()).params(params).query(rowMapper).list();
	}

	@Override
	public Optional<TownshipDto> findById(int id) {
		return client.sql("%s where ts.id = :id".formatted(selectSql)).params(Map.of("id",id)).query(rowMapper).optional();
	}

}
