package com.pyae.pos.service;

import java.time.LocalDate;
import java.util.List;

import com.pyae.pos.input.ShoppingCart;
import com.pyae.pos.output.SaleDetails;
import com.pyae.pos.output.SaleInfo;

public interface SaleService {
	
	int checkOut(ShoppingCart cart);
	
	List<SaleInfo> search(String salePerson, LocalDate from, LocalDate to);
	
	SaleDetails findById(int id);

	

}
