package com.salesianos.triana.dam.servesapplitebackend.entity.user.company.service;

import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
}
