package com.salesianos.triana.dam.servesapplitebackend.security.errorhandling;

public class JwtTokenException extends RuntimeException{

    public JwtTokenException(String msg){
        super(msg);
    }

}
