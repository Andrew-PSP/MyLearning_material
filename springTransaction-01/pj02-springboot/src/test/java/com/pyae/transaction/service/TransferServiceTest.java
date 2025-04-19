package com.pyae.transaction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pyae.transaction.dto.TransferForm;
import com.pyae.transaction.service.aggregate.TransferFormAggregator;


@SpringBootTest
public class TransferServiceTest {

	@Autowired
	private TransferServiceImplAnnotation service;
	
	
	@ParameterizedTest
	@CsvSource("0001,0002,400000,Test Transfer")
	void transfer_test(@AggregateWith(TransferFormAggregator.class) TransferForm form) {
		int id =service.transfer(form);
		assertEquals(1, id);
	}
}
