package com.pyae.transaction.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.pyae.transaction.BusinessException;
import com.pyae.transaction.dto.AccountInfo;
import com.pyae.transaction.dto.AmountUpdateForm;
import com.pyae.transaction.dto.BalanceHistoryForm;
import com.pyae.transaction.dto.TransferForm;
import com.pyae.transaction.event.TransferLogErrorEvent;
import com.pyae.transaction.event.TransferLogSuccessEvent;
import com.pyae.transaction.repo.AccountRepo;
import com.pyae.transaction.repo.BalanceHistoryRepo;
import com.pyae.transaction.repo.TransferLogRepo;
import com.pyae.transaction.repo.TransferRepo;

@Service
public class TransferServiceTemplate implements TransferService{


	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private BalanceHistoryRepo balanceHistoryRepo;
	@Autowired
	private TransferRepo transferRepo;
	@Autowired
	private TransferLogRepo transferLogRepo;
	@Autowired
	private ApplicationEventPublisher publisher;
	
	private TransactionTemplate logTemplate;
	private TransactionTemplate transferTemplate;
	
	public TransferServiceTemplate(PlatformTransactionManager trxManager) {
		logTemplate = new TransactionTemplate(trxManager);
		transferTemplate = new TransactionTemplate(trxManager);
		transferTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	}
	
	
	@Override
	public int transfer(TransferForm form) {
		
		var logId = logTemplate.execute(status -> transferLogRepo.createLog(form));
		
		return transferTemplate.execute(status -> {
			AccountInfo FromAccount = accountRepo.findByAccountId(form.accountFrom())
					.orElseThrow(()-> new BusinessException("Unvalid Id : %s".formatted(form.accountFrom())));		
			
			//Check Amount to transfer
			if(FromAccount.amount() < form.amount()) {
				throw new BusinessException("Not Enough Money from %s".formatted(form.accountFrom()));
			}
			//Initiate transfer Transaction
			var trxId = transferRepo.initiate(form);
			try {
				
				//Create Account From balance history
				var FromHistory = BalanceHistoryForm.builder()
						.accountNum(FromAccount.accountNum()).isDebit(true).lastAmount(FromAccount.amount())
						.remark(form.remark()).trxAmount(form.amount()).trxId(trxId)
						.version(FromAccount.nextVersion()).build();
				balanceHistoryRepo.create(FromHistory);
				
				//update Account From amount
				var FromAmountUpdate = new AmountUpdateForm(FromAccount.accountNum(),FromAccount.amount()-form.amount() ,FromAccount.nextVersion());
				accountRepo.updateAmount(FromAmountUpdate);
				
				//Get Account to information
				AccountInfo ToAccount = accountRepo.findByAccountId(form.accountTo())
						.orElseThrow(()-> new BusinessException("Unvalid Id : %s".formatted(form.accountTo())));		
				
				//create Account To balance history
				var ToHistory = BalanceHistoryForm.builder()
						.accountNum(ToAccount.accountNum()).isDebit(false).lastAmount(ToAccount.amount())
						.remark(form.remark()).trxAmount(form.amount()).trxId(trxId)
						.version(ToAccount.nextVersion()).build();
				balanceHistoryRepo.create(ToHistory);
				System.out.println(1/0);
				//update Account To amount
				var ToAmountUpdate = new AmountUpdateForm(ToAccount.accountNum(),ToAccount.amount()+form.amount() ,ToAccount.nextVersion());
				accountRepo.updateAmount(ToAmountUpdate);
				
				//Update transfer transaction Status;
				transferRepo.updateStatus(trxId, "Sucess");
				
				publisher.publishEvent(new TransferLogSuccessEvent(logId,form.remark()));
			} catch (Exception e) {
				publisher.publishEvent(new TransferLogErrorEvent(logId, e));
				throw e;
			}
			
			return trxId;
		});
		//Get Account From information
		
		
	}

}
