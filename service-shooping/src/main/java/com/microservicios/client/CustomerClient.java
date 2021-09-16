package com.microservicios.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microservicios.model.Customer;

@FeignClient(name = "customer-service")
@RequestMapping("/customers")
public interface CustomerClient {

  @GetMapping(value = "/{id}")
  public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id);
  
}
