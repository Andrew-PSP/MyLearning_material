package com.pyae.dao;

import com.pyae.dto.AccountDto;
import com.pyae.dto.AccountForm;

public interface AccountDao {

	int create(AccountForm form);
	long count();
	AccountDto findById(int id);
}
