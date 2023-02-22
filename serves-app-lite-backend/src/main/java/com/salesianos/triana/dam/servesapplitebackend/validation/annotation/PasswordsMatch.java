package com.salesianos.triana.dam.servesapplitebackend.validation.annotation;

import com.salesianos.triana.dam.servesapplitebackend.validation.validator.PasswordsMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordsMatchValidator.class)
public @interface PasswordsMatch {

    String message() default "Las contraseñas no coinciden";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};

    String passwordField();
    String verifyPasswordField();
}
