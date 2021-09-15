package com.microservicios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicios.entity.Customer;
import com.microservicios.entity.Region;
import com.microservicios.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  CustomerRepository customerRepository;

  @Override
  public List<Customer> findCustomerAll() {

    return customerRepository.findAll();
    
  }

  @Override
  public List<Customer> findCustomersByRegion(Region region) {
  
    return customerRepository.findByRegion(region);

  }

  @Override
  public Customer createCustomer(Customer customer) {

    Customer customerDatabase = customerRepository.findByNumberId(customer.getNumberId());
    
    if (customerDatabase != null) {
      
      return customerDatabase;
      
    }
    
    customer.setState("Created");
    
    customerDatabase = customerRepository.save(customer);
    
    return customerDatabase;
    
  }

  @Override
  public Customer updateCustomer(Customer customer) {

    Customer customerDatabase = getCustomer(customer.getId());
    
    if (customerDatabase == null) {
      
      return null;
      
    }
    
    customerDatabase.setFirstName(customer.getFirstName());
    customerDatabase.setLastName(customer.getLastName());
    customerDatabase.setEmail(customer.getEmail());
    customerDatabase.setPhotoUrl(customer.getPhotoUrl());
    
    return customerRepository.save(customer);
    
  }

  @Override
  public Customer deleteCustomer(Customer customer) {

    Customer customerDatabase = getCustomer(customer.getId());
    
    if (customerDatabase == null) {
      
      return null;
      
    }
    
    customer.setState("Deleted");
    
    return customerRepository.save(customer);
    
  }

  @Override
  public Customer getCustomer(Long id) {

    return customerRepository.findById(id).orElse(null);
    
  }
  
  
  
}
