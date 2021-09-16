package com.microservicios.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicios.client.CustomerClient;
import com.microservicios.client.ProductClient;
import com.microservicios.entity.Invoice;
import com.microservicios.entity.InvoiceItem;
import com.microservicios.model.Customer;
import com.microservicios.model.Product;
import com.microservicios.repository.InvoiceItemsRepository;
import com.microservicios.repository.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {

  @Autowired
  InvoiceRepository invoiceRepository;

  @Autowired
  InvoiceItemsRepository invoiceItemsRepository;
  
  @Autowired
  CustomerClient customerClient;
  
  @Autowired
  ProductClient productClient;
  
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
    
    invoiceDatabase = invoiceRepository.save(invoice);
    invoiceDatabase.getItems().forEach(invoiceItem -> {
      productClient.updateStockProduct(invoiceItem.getProductId(), invoiceItem.getQuantity() * -1);
    });
    
    return invoiceDatabase;
    
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

    Invoice invoice = invoiceRepository.findById(id).orElse(null); 
    
    if (null != invoice) {
      
      Customer customer = customerClient.getCustomer(invoice.getCustomerId()).getBody();
      invoice.setCustomer(customer);
      
      List<InvoiceItem> listItem = invoice.getItems().stream().map(invoiceItem -> {
        Product product = productClient.getProduct(invoiceItem.getProductId()).getBody();
        invoiceItem.setProduct(product);
        return invoiceItem;
      }).collect(Collectors.toList());
      
      invoice.setItems(listItem);
      
    }
    
    return invoice;
    
  }
  
}
