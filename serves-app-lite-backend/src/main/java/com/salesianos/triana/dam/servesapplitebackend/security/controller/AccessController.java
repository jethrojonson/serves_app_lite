package com.salesianos.triana.dam.servesapplitebackend.security.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.dto.UserDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.model.User;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.service.UserService;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.view.UserViews;
import com.salesianos.triana.dam.servesapplitebackend.security.dto.LoginRequest;
import com.salesianos.triana.dam.servesapplitebackend.security.jwt.access.JwtProvider;
import com.salesianos.triana.dam.servesapplitebackend.security.jwt.refresh.RefreshToken;
import com.salesianos.triana.dam.servesapplitebackend.security.jwt.refresh.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AccessController {

    private final UserService userService;
    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    @JsonView(UserViews.UserResponse.class)
    @PostMapping("/register/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO registerNewAdmin (@RequestBody UserDTO newAdmin){
        return UserDTO.of(userService.registerNewAdmin(UserDTO.of(newAdmin)));
    }

    @Transactional
    @JsonView(UserViews.UserWithTokenResponse.class)
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        User user = (User) authentication.getPrincipal();
        refreshTokenService.deleteByUser(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
        return UserDTO.of(user, token, refreshToken.getToken());
    }

}
