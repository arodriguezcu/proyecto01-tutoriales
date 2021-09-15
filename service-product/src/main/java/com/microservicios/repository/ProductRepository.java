package com.microservicios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicios.entity.Category;
import com.microservicios.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

  public List<Product> findByCategory(Category category);
  
}
