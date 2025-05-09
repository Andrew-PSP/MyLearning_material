package com.pyae.jpa.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class InvoiceItemPk {

	private Product product;
	private Invoice  invoice;
}
