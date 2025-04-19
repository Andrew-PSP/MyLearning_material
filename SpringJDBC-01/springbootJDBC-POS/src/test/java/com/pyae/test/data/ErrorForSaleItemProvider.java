package com.pyae.test.data;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import com.pyae.pos.input.SaleItem;
import com.pyae.pos.input.ShoppingCart;

public class ErrorForSaleItemProvider implements ArgumentsProvider {

	
	
	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
		return Stream.of(
				shoppingCartItemError("Thida", "P0001", 3, 0, "Please Enter Unit Price"),
				shoppingCartItemError("Thida", "P0001", 0, 1000, "Please Enter Quantity"),
				shoppingCartItemError("Thida", "", 1, 1000, "Please Enter Product Code"));
	}
	
	private Arguments shoppingCartItemError(String name, String code, int quantity, int price, String errorMessage) {
		var cart = ShoppingCart.withName(name);
		var item = new SaleItem();
		item.setProductCode(code);
		item.setQuantity(quantity);
		item.setUnitPrice(price);
		cart.setItems(List.of(item));
		return Arguments.of(cart, errorMessage);
	}
	

}
