package com.salesianos.triana.dam.servesapplitebackend.validation.annotation;

import com.salesianos.triana.dam.servesapplitebackend.validation.validator.LongIDValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LongIDValidator.class)
@Documented
public @interface LongID {

    String message() default "El ID no es válido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
