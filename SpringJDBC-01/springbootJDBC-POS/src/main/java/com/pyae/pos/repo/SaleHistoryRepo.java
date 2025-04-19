package com.pyae.pos.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.pyae.pos.input.ShoppingCart;
import com.pyae.pos.output.SaleInfo;

public interface SaleHistoryRepo {

	Integer create(ShoppingCart cart);

	List<SaleInfo> search(String salePerson, LocalDate from, LocalDate to);

	Optional<SaleInfo> findById(int id);

}
