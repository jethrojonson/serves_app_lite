package com.salesianos.triana.dam.servesapplitebackend.entity.product.repository;

import com.salesianos.triana.dam.servesapplitebackend.entity.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
