package com.salesianos.triana.dam.servesapplitebackend.entity.product.repository;

import com.salesianos.triana.dam.servesapplitebackend.entity.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

//    @Query("""
//            SELECT p
//            FROM Product p
//            WHERE p.active = true
//            """)
//    List<Product> findAllActive();

    @Query("""
            SELECT p
            FROM Product p
            WHERE p.active = true
            AND p.active = false
            """)
    List<Product> findAllWithInactive();
}
