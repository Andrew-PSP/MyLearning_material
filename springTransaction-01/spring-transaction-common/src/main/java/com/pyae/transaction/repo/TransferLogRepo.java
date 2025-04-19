package com.pyae.transaction.repo;

import com.pyae.transaction.dto.TransferForm;

public interface TransferLogRepo {

	int createLog(TransferForm form);

	void updateStatus(int logId, Exception e, String remark);

}
