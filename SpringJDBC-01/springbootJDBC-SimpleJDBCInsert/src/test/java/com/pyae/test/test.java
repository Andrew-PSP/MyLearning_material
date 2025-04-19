package com.pyae.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pyae.domain.ProductDetail;
import com.pyae.domain.ProductForm;
import com.pyae.repo.ProductRepo;
import com.pyae.test.data.ArguementFormAggregator;
import com.pyae.test.data.ArgumentDetailAggregator;

@SpringBootTest
@TestMethodOrder(value = OrderAnnotation.class)
public class test {

	@Autowired
	ProductRepo repo;
	
	@ParameterizedTest
	@Order(1)
	@CsvFileSource(resources = "/insertProduct.txt", delimiter = '\t')
	void test_insert(int id,@AggregateWith(value = ArguementFormAggregator.class) ProductForm form) {
		var result = repo.insertProduct(form);
		assertEquals(id, result);
	}
	
	
	@ParameterizedTest
	@Order(2)
	@CsvFileSource(resources ="/insertProduct.txt", delimiter = '\t' )
	void test_findById_found(int id,@AggregateWith(value = ArgumentDetailAggregator.class) ProductDetail productDetail ) {
		
		var result = repo.findById(id);
		
		assertEquals(productDetail, result);
		
	}
}
