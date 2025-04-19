package com.pyae.transaction.repo;

import com.pyae.transaction.dto.BalanceHistoryForm;

public interface BalanceHistoryRepo {

	void create(BalanceHistoryForm input);

}
