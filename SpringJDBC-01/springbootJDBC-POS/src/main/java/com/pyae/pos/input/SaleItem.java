package com.pyae.pos.input;

import lombok.Data;

@Data
public class SaleItem {

	private int saleId;
	private String productCode;
	private int unitPrice;
	private int quantity;
}
