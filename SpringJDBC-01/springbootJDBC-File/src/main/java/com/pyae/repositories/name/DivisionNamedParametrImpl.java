package com.pyae.repositories.name;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.pyae.domain.DivisionDto;
import com.pyae.repositories.DivisionRepo;

@Repository
@Profile("name")
public class DivisionNamedParametrImpl implements DivisionRepo {

	@Autowired
	private NamedParameterJdbcTemplate template;
	private RowMapper<DivisionDto> rowMapper;
	
	private static final String SELECT_SQL="""
			select dv.id, dv.name, count(ds.id) districts from division dv
			join district ds on ds.division_id= dv.id""";
	
	private static final String GROUPBY ="group by dv.id,dv.name ";
	
	public DivisionNamedParametrImpl() {
		rowMapper = new DataClassRowMapper<>(DivisionDto.class);
	}
	
	
	@Override
	public List<DivisionDto> search(String name) {
		var sql = new StringBuffer(SELECT_SQL);
		var params = new HashMap<String, Object>();
		
		if(StringUtils.hasLength(name)) {
			sql.append(" where lower(dv.name) like :name");
			params.put("name", name.toLowerCase().concat("%"));
		}
		sql.append(" ").append(GROUPBY);
		
		return template.query(sql.toString(), params, rowMapper);
	}

	@Override
	public Optional<DivisionDto> findById(int id) {
		
		return template.query("%s where dv.id= :id %s".formatted(SELECT_SQL, GROUPBY), Map.of("id",id),rowMapper)
				.stream().findAny();
	}

}
