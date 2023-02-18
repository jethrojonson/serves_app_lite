package com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.service;

import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

}
