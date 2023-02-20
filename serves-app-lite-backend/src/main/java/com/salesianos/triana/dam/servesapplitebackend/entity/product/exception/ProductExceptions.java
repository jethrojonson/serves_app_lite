package com.salesianos.triana.dam.servesapplitebackend.entity.product.exception;

import javax.persistence.EntityNotFoundException;

public interface ProductExceptions {

    public class ProductNotFoundException extends EntityNotFoundException{
        public ProductNotFoundException(){
            super("Product not found");
        }
        public ProductNotFoundException (Long id){
            super(String.format("Could not found product with ID: %x",id));
        }
    }

    public class EmptyProductListException extends EntityNotFoundException{
        public EmptyProductListException (){
            super("No products were found with the search criteria");
        }
    }
}
