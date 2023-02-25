package com.salesianos.triana.dam.servesapplitebackend.entity.item.exception;

import javax.persistence.EntityNotFoundException;

public interface ItemExceptions {

    public class ItemNotFoundException extends EntityNotFoundException {
        public ItemNotFoundException(Long id) {
            super(String.format("Could not found item with ID: %x", id));
        }
    }

    public class ItemNotInMenuException extends RuntimeException{
        public ItemNotInMenuException (Long id){
            super(String.format("The item with ID: %x is not in this company",id));
        }
    }

}
