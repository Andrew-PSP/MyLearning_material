package com.pyae.jpa.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Settlement {

	@Id
	@GeneratedValue
	private UUID id;
	
	@OneToMany(mappedBy = "settlement")
	private List<Invoice> invoices;
	
	@ManyToOne
	private Merchant merchant;
	
	private LocalDateTime settleAt;
	private BigDecimal settleAmount;
	
}
