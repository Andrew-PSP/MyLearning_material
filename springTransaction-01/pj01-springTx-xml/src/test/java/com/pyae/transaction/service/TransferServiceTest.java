package com.pyae.transaction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.pyae.ApplicationConfig;
import com.pyae.transaction.dto.TransferForm;
import com.pyae.transaction.service.aggregate.TransferFormAggregator;

//@SpringJUnitConfig(locations ="classpath:/Application.xml")
@SpringJUnitConfig(classes = ApplicationConfig.class)
//@Sql(
//		scripts = {"classpath:/schema.sql","classpath:/data.sql"},
//		executionPhase = ExecutionPhase.BEFORE_TEST_CLASS
//		)
public class TransferServiceTest {

	@Autowired
	private TransferService service;
	
	
	@ParameterizedTest
	@CsvSource("0001,0002,50000,Test Transfer")
	void transfer_test(@AggregateWith(TransferFormAggregator.class) TransferForm form) {
		int id =service.transfer(form);
		assertEquals(1, id);
	}
}
