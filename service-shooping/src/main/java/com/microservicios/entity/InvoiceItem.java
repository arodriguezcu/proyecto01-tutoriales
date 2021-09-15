package com.microservicios.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_invoice_items")
public class InvoiceItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Positive(message = "Stock debe ser mayor que cero.")
  private Double quantity;
  
  private Double price;
  
  @Column(name = "product_id")
  private Long productId;
  
  @Transient
  private Double subTotal;
  
  public Double getSubTotal() {
    
    if (this.price > 0 && this.quantity > 0) {
      
      return this.quantity * this.price;
      
    } else {
      
      return 0.0;
      
    }
    
  }
  
  public InvoiceItem() {
    
    this.quantity = 0.0;
    this.price = 0.0;
    
  }
  
}
