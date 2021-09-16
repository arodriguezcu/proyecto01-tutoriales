package com.microservicios.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservicios.model.Customer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@FeignClient(name = "customer-service", fallback = CustomerHystrixFallbackFactory.class)
public interface CustomerClient {

  @HystrixCommand
  @GetMapping(value = "/customers/{id}")
  public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id);
  
}
