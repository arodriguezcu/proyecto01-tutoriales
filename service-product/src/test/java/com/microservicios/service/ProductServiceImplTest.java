package com.microservicios.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.microservicios.entity.Category;
import com.microservicios.entity.Product;
import com.microservicios.repository.ProductRepository;

@SpringBootTest
class ProductServiceImplTest {

  @Mock
  private ProductRepository productRepository;
  
  private ProductService productService;
  
  @SuppressWarnings("deprecation")
  @BeforeEach
  public void setup() {
    
    MockitoAnnotations.initMocks(this);
    
    productService = new ProductServiceImpl(productRepository);
    
    Product product = Product.builder()
        .id(1L)
        .name("Computer")
        .category(Category.builder()
            .id(1L)
            .build())
        .price(12.5)
        .stock(5.0)
        .build();
    
    Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
    
    Mockito.when(productRepository.save(product)).thenReturn(product);
    
  }
  
  @Test
  public void whenValidGetId_ThenReturnProduct() {
    
    Product found = productService.getProduct(1L);
    
    Assertions.assertThat(found.getName()).isEqualTo("Computer");
    
  }

  @Test
  public void whenValidUpdateStock_ThenReturnNewStock() {
    
    Product newStock = productService.updateStock(1L, 8.0);
    
    Assertions.assertThat(newStock.getStock()).isEqualTo(13);
    
  }

}
