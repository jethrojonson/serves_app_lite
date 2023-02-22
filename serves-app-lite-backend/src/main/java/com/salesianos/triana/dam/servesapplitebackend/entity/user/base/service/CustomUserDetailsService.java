package com.salesianos.triana.dam.servesapplitebackend.entity.user.base.service;

import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("No user with username " + username)
                );
    }
}
