package com.pyae.transaction.eventListener;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.pyae.transaction.event.TransferLogErrorEvent;
import com.pyae.transaction.event.TransferLogSuccessEvent;
import com.pyae.transaction.repo.TransferLogRepo;

public class TransferLogEventListener {

	private TransferLogRepo repo;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void onSuccess(TransferLogSuccessEvent event) {
		repo.updateStatus(event.logId(), null, event.remark());
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
	public void onError(TransferLogErrorEvent event) {
		repo.updateStatus(event.logId(), event.e(), null);
	}
	
}
