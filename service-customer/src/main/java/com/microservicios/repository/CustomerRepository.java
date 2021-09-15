package com.microservicios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicios.entity.Customer;
import com.microservicios.entity.Region;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  public Customer findByNumberId(String numberId);
  
  public List<Customer> findByLastName(String lastName);
  
  public List<Customer> findByRegion(Region region);
  
}
