package com.salesianos.triana.dam.servesapplitebackend.entity.product.repository;

import com.salesianos.triana.dam.servesapplitebackend.entity.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
            SELECT p 
            FROM Product p 
            WHERE p.active = true
            """)
    List<Product> findAllActive();
}
