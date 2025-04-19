package com.pyae.transaction.repo;

import com.pyae.transaction.dto.TransferForm;

public interface TransferRepo {

	int initiate(TransferForm input);

	void updateStatus(int trxId, String status);


}
