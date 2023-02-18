package com.salesianos.triana.dam.servesapplitebackend.entity.product.controller;

import com.salesianos.triana.dam.servesapplitebackend.entity.product.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Tag(name = "Product Controller", description = "Controller of the products operations")
public class ProductController {

    private final ProductService productService;

}
