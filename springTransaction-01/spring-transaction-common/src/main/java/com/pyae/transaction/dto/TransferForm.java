package com.pyae.transaction.dto;

public record TransferForm(String accountFrom, String accountTo, int amount, String remark) {

}
