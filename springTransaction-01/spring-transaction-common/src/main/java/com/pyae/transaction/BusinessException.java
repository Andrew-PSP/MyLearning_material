package com.pyae.transaction;

import com.pyae.transaction.repo.TransferLogRepo;

public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TransferLogRepo repo;

	public BusinessException(String message) {
		super(message);
	}

	
}
