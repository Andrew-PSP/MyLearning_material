package com.pyae.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
@IdClass(InvoiceItemPk.class)
public class InvoiceItem {
	

	@Id
	@ManyToOne
	private Product product;
	@Id
	@ManyToOne
	private Invoice  invoice;

	private int quantity;
	private int purchasePrice;
}
