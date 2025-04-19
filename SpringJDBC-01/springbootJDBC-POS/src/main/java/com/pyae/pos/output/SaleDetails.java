package com.pyae.pos.output;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class SaleDetails {

	private int id;
	private String salePerson;
	private LocalDate saleAt;
	private List<SaleDetailsItem> items;
	
	public int count() {
		return items.stream().mapToInt(a -> a.getQuantity()).sum();
	}
	
	public int total() {
		return items.stream().mapToInt(a -> a.total()).sum();
	}

	public static SaleDetails from(SaleInfo saleInfo, List<SaleDetailsItem> items) {
		var details = new SaleDetails();
		details.setId(saleInfo.getId());
		details.setSalePerson(saleInfo.getSalePerson());
		details.setSaleAt(saleInfo.getSaleAt());
		details.setItems(items);
		
		return details;
	}
	
}
