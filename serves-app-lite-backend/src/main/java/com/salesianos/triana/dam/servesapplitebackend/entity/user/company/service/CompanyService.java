package com.salesianos.triana.dam.servesapplitebackend.entity.user.company.service;

import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.roles.UserRole;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.dto.CompanyDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.model.Company;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private PasswordEncoder passwordEncoder;
    private final CompanyRepository companyRepository;

    public Company createNewCompany(Company newCompany){
        Company company = Company.builder()
                .cif(newCompany.getCif())
                .companyName(newCompany.getCompanyName())
                .username(newCompany.getUsername())
                .password(newCompany.getPassword())
                .avatar(newCompany.getAvatar())
                .roles(Set.of(UserRole.COMPANY))
                .build();
        return companyRepository.save(company);
    }

    public Optional<Company> findByUsername(String username){
        return companyRepository.findFirstByUsername(username);
    }

    public List<CompanyDTO> getAllCompanies(){
        return companyRepository.findAll().stream().map(CompanyDTO::of).toList();
    }

    public CompanyDTO getCompanyById(UUID id){
        Company result = companyRepository.findById(id).get();
        return CompanyDTO.of(result);
    }

    public CompanyDTO updateACompany(CompanyDTO toUpdate, UUID id){
        Company result = companyRepository.findById(id).get();
        result.setCompanyName(toUpdate.getCompanyName());
        return CompanyDTO.of(companyRepository.save(result));
    }

    public void removeACompany (UUID id){
        companyRepository.delete(companyRepository.findById(id).get());
    }
}
