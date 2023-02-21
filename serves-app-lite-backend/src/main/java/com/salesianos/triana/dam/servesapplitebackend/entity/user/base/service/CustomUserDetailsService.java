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

    private final CompanyService companyService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return companyService.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(
                        String.format("No user with username \"%s\"",username))
                );
    }
}
