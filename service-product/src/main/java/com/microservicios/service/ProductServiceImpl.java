package com.microservicios.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.microservicios.entity.Category;
import com.microservicios.entity.Product;
import com.microservicios.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public List<Product> listAllProduct() {

    return productRepository.findAll();
    
  }

  @Override
  public Product getProduct(Long id) {

    return productRepository.findById(id).orElse(null);
    
  }

  @Override
  public Product createProduct(Product product) {

    product.setStatus("Created");
    product.setCreateAt(new Date());
    
    return productRepository.save(product);
    
  }

  @Override
  public Product updateProduct(Product product) {

    Product productDatabase = getProduct(product.getId());
    
    if (null == productDatabase) {
      return null;
    }
    
    productDatabase.setName(product.getName());
    productDatabase.setDescription(product.getDescription());
    productDatabase.setCategory(product.getCategory());
    productDatabase.setPrice(product.getPrice());
    
    return productRepository.save(productDatabase);
    
  }

  @Override
  public Product deleteProduct(Long id) {

    Product productDatabase = getProduct(id);
    
    if (null == productDatabase) {
      return null;
    }
    
    productDatabase.setStatus("Deleted");
    
    return productRepository.save(productDatabase);
    
  }

  @Override
  public List<Product> findByCategory(Category category) {

    return productRepository.findByCategory(category);
    
  }

  @Override
  public Product updateStock(Long id, Double quantity) {

    Product productDatabase = getProduct(id);
    
    if (null == productDatabase) {
      return null;
    }
    
    Double stock = productDatabase.getStock() + quantity;
    
    productDatabase.setStock(stock);
    
    return productRepository.save(productDatabase);
  }
  
}
