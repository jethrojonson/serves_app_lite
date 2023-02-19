package com.salesianos.triana.dam.servesapplitebackend.entity.user.company.service;

import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.dto.CompanyDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.model.Company;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyDTO addNewCompany(CompanyDTO newCompany){
        return CompanyDTO.of(companyRepository.save(CompanyDTO.of(newCompany)));
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
