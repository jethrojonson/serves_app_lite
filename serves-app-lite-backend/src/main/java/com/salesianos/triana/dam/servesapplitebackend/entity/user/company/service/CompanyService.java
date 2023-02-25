package com.salesianos.triana.dam.servesapplitebackend.entity.user.company.service;

import com.salesianos.triana.dam.servesapplitebackend.entity.item.exception.ItemExceptions;
import com.salesianos.triana.dam.servesapplitebackend.entity.item.model.Item;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.model.Order;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.exception.ProductExceptions;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.model.Product;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.model.roles.UserRole;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.repository.UserRepository;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.dto.CompanyDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.exception.CompanyExceptions;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.model.Company;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.repository.CompanyRepository;
import com.salesianos.triana.dam.servesapplitebackend.search.spec.GenericSpecificationBuilder;
import com.salesianos.triana.dam.servesapplitebackend.search.util.SearchCriteria;
import com.salesianos.triana.dam.servesapplitebackend.search.util.SearchCriteriaExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Item addProductToMenu(Company company, Long product_id, Item toAdd) {
        company = companyRepository.findById(company.getId())
                .orElseThrow(CompanyExceptions.CompanyNotFoundException::new);
        Product product = companyRepository.findProductById(product_id)
                .orElseThrow(ProductExceptions.ProductNotFoundException::new);
        toAdd.setProduct(product);
        company.addItemToCompany(toAdd);
        companyRepository.save(company);
        return toAdd;
    }

    public Company getCompany(String company_id) {
        UUID id = UUID.fromString(company_id);
        Company company = companyRepository.findById(id)
                .orElseThrow(CompanyExceptions.CompanyNotFoundException::new);
        company.setOrdersReceived(companyRepository.loadOrdersReceived(id));
        return company;
//        return companyRepository.findById(id)
//                .orElseThrow(CompanyExceptions.CompanyNotFoundException::new);
    }

    public Page<Company> searchAll(String searchCriteria, Pageable pageable) {
        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(searchCriteria);
        GenericSpecificationBuilder companySpecificationBuilder =
                GenericSpecificationBuilder.builder()
                        .params(params)
                        .type(Company.class)
                        .build();
        Specification<Company> spec = companySpecificationBuilder.build();
        Page<Company> result = companyRepository.findAll(spec, pageable);
        if (result.isEmpty())
            throw new CompanyExceptions.CompanyNotFoundException();
        result.forEach(c->c.setOrdersReceived(companyRepository.loadOrdersReceived(c.getId())));
        return result;
    }

    public void deleteItemFromMenu(Company company, Long item_id) {
        company = companyRepository.findById(company.getId())
                .orElseThrow(CompanyExceptions.CompanyNotFoundException::new);
        Item item = companyRepository.findItemById(item_id)
                .orElseThrow(() -> new ItemExceptions.ItemNotFoundException(item_id));
        if(company.getMenuItems().stream().anyMatch(i-> i.getId()==item_id))
           company.removeItemFromCompany(item);
        else
            throw new ItemExceptions.ItemNotInMenuException(item_id);
        companyRepository.save(company);
    }

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    public Optional<Company> findByCompanyName(String companyName) {
        return companyRepository.findFirstByCompanyName(companyName);
    }


//
//
//
//
//
//    public Company createNewCompany(Company newCompany){
//        Company company = Company.builder()
//                .cif(newCompany.getCif())
//                .companyName(newCompany.getCompanyName())
//                .username(newCompany.getUsername())
//                .password(newCompany.getPassword())
//                .avatar(newCompany.getAvatar())
//                .roles(Set.of(UserRole.COMPANY))
//                .build();
//        return companyRepository.save(company);
//    }
//
//    public Optional<Company> findByUsername(String username){
//        return companyRepository.findFirstByUsername(username);
//    }
//
//    public List<CompanyDTO> getAllCompanies(){
//        return companyRepository.findAll().stream().map(CompanyDTO::of).toList();
//    }
//
//    public CompanyDTO getCompanyById(UUID id){
//        Company result = companyRepository.findById(id).get();
//        return CompanyDTO.of(result);
//    }
//
//    public CompanyDTO updateACompany(CompanyDTO toUpdate, UUID id){
//        Company result = companyRepository.findById(id).get();
//        result.setCompanyName(toUpdate.getCompanyName());
//        return CompanyDTO.of(companyRepository.save(result));
//    }
//
//    public void removeACompany (UUID id){
//        companyRepository.delete(companyRepository.findById(id).get());
//    }
}
