package com.pyae.jpa.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Invoice extends SecurityInfo {

	@Id
	@GeneratedValue
	private UUID id;
	
	@ManyToOne
	private Merchant merchant;
	private LocalDateTime invoiceDate;
	private LocalDateTime dueDate;
	private BigDecimal invoiceTotal;
	
	@ManyToOne
	private Settlement settlement;
}
