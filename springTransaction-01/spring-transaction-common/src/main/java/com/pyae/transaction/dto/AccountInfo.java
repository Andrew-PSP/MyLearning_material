package com.pyae.transaction.dto;

public record AccountInfo(String accountNum,
		String accountName,
		String phone,
		int amount,
		int version) {

	public int nextVersion() {
		// TODO Auto-generated method stub
		return version +1;
	}

}
