package com.salesianos.triana.dam.servesapplitebackend.validation.validator;

import com.salesianos.triana.dam.servesapplitebackend.validation.annotation.LongID;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LongIDValidator implements ConstraintValidator<LongID, String> {



    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
           Long.parseLong(value);
           return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
