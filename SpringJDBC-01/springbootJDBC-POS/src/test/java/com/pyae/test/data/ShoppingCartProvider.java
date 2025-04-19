package com.pyae.test.data;

import static org.junit.jupiter.api.DynamicTest.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import com.pyae.pos.input.SaleItem;
import com.pyae.pos.input.ShoppingCart;

public class ShoppingCartProvider implements ArgumentsProvider{
	
	
	private static Stream<Arguments> provide(){
		return Stream.of(
				Arguments.of(cartWithOneItem()),
				Arguments.of(cartWithNoItems()),
				Arguments.of(cartWithNoUserName()));
	}
	
	private static ShoppingCart cartWithOneItem() {
		var cart = new ShoppingCart();
		cart.setSalePerson("Thida");
		var saleItem = new SaleItem();
		saleItem.setProductCode("P0001");
		saleItem.setQuantity(3);
		saleItem.setUnitPrice(2);
		cart.setItems(List.of(saleItem));
		return cart;
	}
	
	private static ShoppingCart cartWithNoItems() {
		var cart = new ShoppingCart();
		cart.setSalePerson("Mya");
		return cart;
	}
	
	private static ShoppingCart cartWithNoUserName() {
		var cart = new ShoppingCart();
		cart.setItems(new ArrayList<SaleItem>());
		return cart;
	}
	
	

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
		return provide();
	}
	
	
	

}
