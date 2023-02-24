package com.salesianos.triana.dam.servesapplitebackend.entity.product.service;

import com.salesianos.triana.dam.servesapplitebackend.entity.product.exception.ProductExceptions;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.model.Product;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.repository.ProductRepository;
import com.salesianos.triana.dam.servesapplitebackend.search.spec.GenericSpecificationBuilder;
import com.salesianos.triana.dam.servesapplitebackend.search.util.SearchCriteria;
import com.salesianos.triana.dam.servesapplitebackend.search.util.SearchCriteriaExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product addNewProduct(Product newProduct){
       return productRepository.save(newProduct);
    }

    public Page<Product> searchActive(String searchCriteria, Pageable pageable){
        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(searchCriteria);
        GenericSpecificationBuilder productSpecificationBuilder =
                GenericSpecificationBuilder.builder()
                        .params(params)
                        .type(Product.class)
                        .build();
        Specification<Product> spec = productSpecificationBuilder.build();
        Page<Product> result = productRepository.findAll(spec, pageable);
        if(result.isEmpty())
            throw new ProductExceptions.EmptyProductListException();
        return result;
    }

    public Page<Product> searchAll(String searchCriteria, Pageable pageable){
        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(searchCriteria);
        GenericSpecificationBuilder productSpecificationBuilder =
                GenericSpecificationBuilder.builder()
                        .params(params)
                        .type(Product.class)
                        .build();
        Specification<Product> spec = productSpecificationBuilder.build();
        Page<Product> result = productRepository.findAll(spec, pageable);
        if(result.isEmpty())
            throw new ProductExceptions.EmptyProductListException();
        return result;
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new ProductExceptions.ProductNotFoundException(id));
    }

//    public Product updateAProduct(Product toUpdate, Long id){
//        return productRepository.findById(id).map(p ->{
//            p.setCategory(toUpdate.getCategory());
//            p.setPrice(toUpdate.getPrice());
//            return productRepository.save(p);
//                }).orElseThrow(ProductExceptions.ProductNotFoundException::new);
//    }

    public void retireAProduct(Long id){
        productRepository.delete(
                productRepository.findById(id).orElseThrow(ProductExceptions.ProductNotFoundException::new)
        );
    }
}
