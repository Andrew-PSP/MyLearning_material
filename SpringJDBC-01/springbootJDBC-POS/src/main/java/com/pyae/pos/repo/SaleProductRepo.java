package com.pyae.pos.repo;

import java.util.List;

import com.pyae.pos.input.SaleItem;
import com.pyae.pos.output.SaleDetailsItem;

public interface SaleProductRepo {

	void create(int id, SaleItem item);

	List<SaleDetailsItem> findBySaleHistoryId(int SaleHistoryId);

}
