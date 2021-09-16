package com.microservicios.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.microservicios.model.Customer;

@Component
public class CustomerHystrixFallbackFactory implements CustomerClient {

  @Override
  public ResponseEntity<Customer> getCustomer(Long id) {

    Customer customer = Customer.builder()
        .firstName("None")
        .lastName("None")
        .email("None")
        .photoUrl("None")
        .build();
    
    return ResponseEntity.ok(customer);
    
  }

}
