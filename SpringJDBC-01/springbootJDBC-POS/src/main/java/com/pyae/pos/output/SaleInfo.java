package com.pyae.pos.output;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SaleInfo {
	private int id;
	private String salePerson;
	private LocalDate saleAt;
	private long itemCount;
	private long totalAmount;
}
