package com.microservicios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicios.entity.Invoice;
import com.microservicios.repository.InvoiceItemsRepository;
import com.microservicios.repository.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {

  @Autowired
  InvoiceRepository invoiceRepository;

  @Autowired
  InvoiceItemsRepository invoiceItemsRepository;
  
  @Override
  public List<Invoice> findInvoiceAll() {

    return  invoiceRepository.findAll();
    
  }

  @Override
  public Invoice createInvoice(Invoice invoice) {
    
    Invoice invoiceDatabase = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());
    
    if (invoiceDatabase != null){
        
      return invoiceDatabase;
      
    }
        
    invoice.setState("CREATED");
    
    return invoiceRepository.save(invoice);
    
  }

  @Override
  public Invoice updateInvoice(Invoice invoice) {
    
    Invoice invoiceDatabase = getInvoice(invoice.getId());
    
    if (invoiceDatabase == null){
      
        return null;
        
    }
    
    invoiceDatabase.setCustomerId(invoice.getCustomerId());
    invoiceDatabase.setDescription(invoice.getDescription());
    invoiceDatabase.setNumberInvoice(invoice.getNumberInvoice());
    invoiceDatabase.getItems().clear();
    invoiceDatabase.setItems(invoice.getItems());
    
    return invoiceRepository.save(invoiceDatabase);
    
  }

  @Override
  public Invoice deleteInvoice(Invoice invoice) {

    Invoice invoiceDatabase = getInvoice(invoice.getId());
    
    if (invoiceDatabase == null){
      
        return null;
        
    }
    
    invoiceDatabase.setState("DELETED");
    
    return invoiceRepository.save(invoiceDatabase);
    
  }

  @Override
  public Invoice getInvoice(Long id) {

    return invoiceRepository.findById(id).orElse(null);
    
  }
  
}
