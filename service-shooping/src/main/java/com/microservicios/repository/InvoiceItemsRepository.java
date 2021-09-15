package com.microservicios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicios.entity.InvoiceItem;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem, Long> {

}
