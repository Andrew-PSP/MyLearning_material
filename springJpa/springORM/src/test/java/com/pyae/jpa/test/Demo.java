package com.pyae.jpa.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Comparator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pyae.jpa.entity.Customer;
import com.pyae.jpa.entity.Invoice;
import com.pyae.jpa.entity.Invoice.Status;
import com.pyae.jpa.entity.InvoiceId;
import com.pyae.jpa.repo.CustomerRepo;
import com.pyae.jpa.repo.InvoiceRepo;

@SpringBootTest
public class Demo {

	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private InvoiceRepo invoiceRepo;
	
	@Test
	void demoTest() {
		var customer = new Customer();
		customer.setName("Pyae");
		customer.setPhone("09795850545");
		customer.setDob(LocalDate.of(2003,4,17));
		
		customerRepo.save(customer);
		
		var invoice = new Invoice();
		invoice.setAmount(100000);
		invoice.setDueDate(LocalDate.of(2025, 7, 11));
		invoice.setInvoiceId(1);
		invoice.setInvoiceDate(LocalDate.now());
		invoice.setStatus(Status.UNPAID);
		
		invoiceRepo.save(invoice);
		
		var invoiceId =new InvoiceId();
		invoiceId.setInvoiceId(1);
		invoiceId.setInvoiceDate(LocalDate.now());
		var id = invoiceRepo.findById(invoiceId).orElseThrow();
		
		assertEquals(1, id.getInvoiceId());
	
	}
}
