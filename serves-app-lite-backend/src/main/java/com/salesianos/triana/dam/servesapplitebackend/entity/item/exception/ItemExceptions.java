package com.salesianos.triana.dam.servesapplitebackend.entity.item.exception;

import javax.persistence.EntityNotFoundException;

public interface ItemExceptions {

    public class ItemNotFoundException extends EntityNotFoundException{
        public ItemNotFoundException(Long id){
            super(String.format("Could not found item with ID: %x",id));
        }
    }

}
