package com.pyae.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pyae.jpa.entity.Invoice;
import com.pyae.jpa.entity.InvoiceId;

public interface InvoiceRepo extends JpaRepository<Invoice, InvoiceId> {

}
