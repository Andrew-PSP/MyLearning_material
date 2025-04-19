package com.pyae.transaction.repo;

import java.util.Optional;

import com.pyae.transaction.dto.AccountInfo;
import com.pyae.transaction.dto.AmountUpdateForm;

public interface AccountRepo {

	Optional<AccountInfo> findByAccountId(String id);

	void updateAmount(AmountUpdateForm input);

}
