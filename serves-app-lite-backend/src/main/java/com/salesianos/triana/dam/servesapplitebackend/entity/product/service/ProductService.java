package com.salesianos.triana.dam.servesapplitebackend.entity.product.service;

import com.salesianos.triana.dam.servesapplitebackend.entity.product.dto.ProductDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.model.Product;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDTO addNewProduct(ProductDTO newProduct){
        Product added = productRepository.save(ProductDTO.of(newProduct));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(added.getId()).toUri();

        ProductDTO addedRes = ProductDTO.of(added);
        addedRes.setUri(uri);
        return addedRes;
    }

    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll().stream().map(ProductDTO::of).toList();
    }

    public List<ProductDTO> getAllActiveProducts(){
        return productRepository.findAllActive().stream().map(ProductDTO::of).toList();
    }

    public ProductDTO getProductById(Long id) {
        Product result = productRepository.findById(id).get();
        return ProductDTO.of(result);
    }

    public ProductDTO updateAProduct(ProductDTO toUpdate, Long id){
        Product result = productRepository.findById(id).get();
        result.setCategory(toUpdate.getCategory());
        result.setPrice(toUpdate.getPrice());
        return ProductDTO.of(productRepository.save(result));
    }

    public ProductDTO retireAProduct(Long id){
        Product result = productRepository.findById(id).get();
        result.setActive(false);
        return ProductDTO.of(productRepository.save(result));
    }
}
