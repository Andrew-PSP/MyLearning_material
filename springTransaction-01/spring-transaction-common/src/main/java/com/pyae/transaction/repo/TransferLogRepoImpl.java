package com.pyae.transaction.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SimplePropertySqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pyae.transaction.dto.TransferForm;

@Repository
public class TransferLogRepoImpl implements TransferLogRepo{
	
	private static final String INSERT_SQL="insert into TRANSFER_LOG(account_from,account_to,amount,remark) values(:accountFrom,:accountTo,:amount,:remark)";
	private static final String UPDATE_SQL="update TRANSFER_LOG set status= :status, remark = :remark where id = :logId";
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public int createLog(TransferForm form) {
		return template.update(INSERT_SQL, new SimplePropertySqlParameterSource(form), new GeneratedKeyHolder());
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void updateStatus(int logId, Exception e, String remark) {
		template.update(UPDATE_SQL,
				new MapSqlParameterSource()
				.addValue("logId",logId)
				.addValue("status", e==null? "Success":"Error")
				.addValue("remark", e == null ? remark : e.getMessage()));
	}

}
