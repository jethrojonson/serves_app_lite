package com.salesianos.triana.dam.servesapplitebackend.entity.user.company.exception;

import javax.persistence.EntityNotFoundException;

public interface CompanyExceptions {

    public class CompanyNotFoundException extends EntityNotFoundException{
        public CompanyNotFoundException(){
            super("Company not found");
        }
        public CompanyNotFoundException(String companyName){
            super(String.format("Could not found company with company_name: %s",companyName));
        }
    }
}
