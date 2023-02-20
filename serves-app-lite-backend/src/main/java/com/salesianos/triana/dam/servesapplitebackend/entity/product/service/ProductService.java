package com.salesianos.triana.dam.servesapplitebackend.entity.product.service;

import com.salesianos.triana.dam.servesapplitebackend.entity.product.dto.ProductDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.exception.ProductExceptions;
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

    public Product addNewProduct(Product newProduct){
       return productRepository.save(newProduct);
    }

//    public List<ProductDTO> getAllProducts(){
//        return productRepository.findAll().stream().map(ProductDTO::of).toList();
//    }

    public List<ProductDTO> getAllProducts(){
        List<Product> result = productRepository.findAll();
        if (result.isEmpty())
            throw new ProductExceptions.EmptyProductListException();
        return productRepository.findAllActive().stream().map(ProductDTO::of).toList();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new ProductExceptions.ProductNotFoundException(id));
    }

    public Product updateAProduct(Product toUpdate, Long id){
        return productRepository.findById(id).map(p ->{
            p.setCategory(toUpdate.getCategory());
            p.setPrice(toUpdate.getPrice());
            return productRepository.save(p);
                }).orElseThrow(()-> new ProductExceptions.ProductNotFoundException());
    }

    public void retireAProduct(Long id){
        productRepository.delete(
                productRepository.findById(id).orElseThrow(()-> new ProductExceptions.ProductNotFoundException())
        );
    }
}
