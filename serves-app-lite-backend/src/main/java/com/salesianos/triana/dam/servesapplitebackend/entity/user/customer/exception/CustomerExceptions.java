package com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.exception;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public interface CustomerExceptions {

    public class CustomerNotFoundException extends EntityNotFoundException{
        public CustomerNotFoundException(){
            super("Customer not found");
        }
        public CustomerNotFoundException(UUID id){
            super(String.format("Could not found customer with ID: %s",id/*.toString()*/));
        }
    }
}
