package com.microservicios.repository;

import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.microservicios.entity.Category;
import com.microservicios.entity.Product;

@DataJpaTest
class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

  @Test
  public void whenFindByCategory_thenReturnListProduct() {
    
    Product product1 = Product.builder()
        .name("Computer")
        .category(Category.builder()
            .id(1L)
            .build())
        .description("")
        .stock(10.0)
        .price(1240.99)
        .status("Created")
        .createAt(new Date())
        .build();
    
    productRepository.save(product1);
    
    List<Product> founds = productRepository.findByCategory(product1.getCategory());
    
    Assertions.assertThat(founds.size()).isEqualTo(3);
    
  }
  
}
