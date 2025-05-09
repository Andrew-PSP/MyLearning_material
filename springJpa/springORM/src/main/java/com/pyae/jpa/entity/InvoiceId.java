package com.pyae.jpa.entity;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class InvoiceId {

	private int invoiceId;
	private LocalDate invoiceDate;
}
