package com.salesianos.triana.dam.servesapplitebackend.entity.product.service;

import com.salesianos.triana.dam.servesapplitebackend.entity.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

}
