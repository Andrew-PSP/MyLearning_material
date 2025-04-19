package com.pyae.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pyae.transaction.BusinessException;
import com.pyae.transaction.dto.AccountInfo;
import com.pyae.transaction.dto.AmountUpdateForm;
import com.pyae.transaction.dto.BalanceHistoryForm;
import com.pyae.transaction.dto.TransferForm;
import com.pyae.transaction.repo.AccountRepo;
import com.pyae.transaction.repo.BalanceHistoryRepo;
import com.pyae.transaction.repo.TransferLogRepo;
import com.pyae.transaction.repo.TransferRepo;

@Service
public class TransferServiceImplAnnotation implements TransferService{

	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private BalanceHistoryRepo balanceHistoryRepo;
	@Autowired
	private TransferRepo transferRepo;
	@Autowired
	private TransferLogRepo logRepo;
	
	@Transactional
	@Override
	public int transfer(TransferForm form) {
		
		var logId = logRepo.createLog(form);

		//Get Account From information
		AccountInfo FromAccount = accountRepo.findByAccountId(form.accountFrom())
				.orElseThrow(()-> new BusinessException("Unvalid Id : %s".formatted(form.accountFrom())));		
		
		//Check Amount to transfer
		if(FromAccount.amount() < form.amount()) {
			var e = new BusinessException("Not Enough Money from %s".formatted(form.accountFrom()));
			logRepo.updateStatus(logId, e, null);
			throw e;
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
			
			logRepo.updateStatus(logId, null, form.remark());
		} catch (Exception e) {
			logRepo.updateStatus(logId, e, null);
			throw e;
		}
		
		return trxId;
	}

}
