package com.salesianos.triana.dam.servesapplitebackend.security.jwt.refresh;

import com.salesianos.triana.dam.servesapplitebackend.security.errorhandling.JwtTokenException;
import io.jsonwebtoken.JwtException;

public class RefreshTokenException extends JwtTokenException {

    public RefreshTokenException(String msg){
        super(msg);
    }

}
