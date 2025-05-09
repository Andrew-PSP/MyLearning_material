package com.pyae.jpa.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

@Entity
@Data
@IdClass(InvoiceId.class)
public class Invoice {
	
	@Id
	private int invoiceId;
	@Id
	private LocalDate invoiceDate;

	private int amount;
	private LocalDate dueDate;
	private String remark;
	@Enumerated(EnumType.STRING)
	private Status status;
	
	
	
	public enum Status{
		PAID,UNPAID,OVERDUE
	}
}
