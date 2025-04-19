package com.pyae.pos.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pyae.pos.exception.PosBusinessException;
import com.pyae.pos.input.ShoppingCart;
import com.pyae.pos.output.SaleDetails;
import com.pyae.pos.output.SaleInfo;
import com.pyae.pos.repo.ProductRepo;
import com.pyae.pos.repo.SaleHistoryRepo;
import com.pyae.pos.repo.SaleProductRepo;

@Service
public class SaleServiceImpl implements SaleService {

	@Autowired
	private SaleHistoryRepo saleHistoryRepo;
	@Autowired
	private SaleProductRepo saleProductRepo;
	

	
	@Override
	public int checkOut(ShoppingCart cart) {
			
			validate(cart);
			
			// create sale history
			var saleId = saleHistoryRepo.create(cart);
			
			for(var item : cart.getItems()) {
				saleProductRepo.create(saleId, item);
			}
			
			return saleId;
		}

	private void validate(ShoppingCart cart) {
		
		if( null == cart) {
			throw new PosBusinessException("Cart must not be null");
		}
		if( null == cart.getSalePerson()) {
			throw new PosBusinessException("Please Enter Sale Person");
		}
		if( null == cart.getItems() || cart.getItems().isEmpty()) {
			throw new PosBusinessException("Please Enter Sale Items");
		}
		
		for(var item : cart.getItems()) {
			if(!StringUtils.hasLength(item.getProductCode())) {
				throw new PosBusinessException("Please Enter Product Code");
			}
			if( item.getQuantity() <=0) {
				throw new PosBusinessException("Please Enter Quantity");
			}
			if(item.getUnitPrice() <=0) {
				throw new PosBusinessException("Please Enter Unit Price");
			}
		}
		
	}

	@Override
	public List<SaleInfo> search(String salePerson, LocalDate from, LocalDate to) {
		return saleHistoryRepo.search(salePerson,from,to);
	}

	@Override
	public SaleDetails findById(int id) {
		var saleInfo = saleHistoryRepo.findById(id).orElseThrow(()->(new PosBusinessException("No valid ID")));
		var items = saleProductRepo.findBySaleHistoryId(id);
		return SaleDetails.from(saleInfo,items);
	}

}
