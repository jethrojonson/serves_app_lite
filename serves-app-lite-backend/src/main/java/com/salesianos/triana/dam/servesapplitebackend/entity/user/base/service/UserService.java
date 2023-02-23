package com.salesianos.triana.dam.servesapplitebackend.entity.user.base.service;

import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.model.User;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.model.roles.UserRole;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.repository.UserRepository;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.model.Company;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.service.CompanyService;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.model.Customer;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CustomerService customerService;
    private final CompanyService companyService;

    public Optional<User> findByUsername(String username){
        return userRepository.findFirstByUsername(username);
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public User registerNewAdmin(User newAdmin) {
        return userRepository.save(
                User.builder()
                        .username(newAdmin.getUsername())
                        .password(passwordEncoder.encode(newAdmin.getPassword()))
                        .avatar(newAdmin.getAvatar())
                        .roles(Set.of(UserRole.ADMIN))
                        .build()
        );
    }

    public Customer registerNewCustomer(Customer newCustomer){
        return customerService.addCustomer(
                Customer.builder()
                        .username(newCustomer.getUsername())
                        .password(passwordEncoder.encode(newCustomer.getPassword()))
                        .avatar(newCustomer.getAvatar())
                        .roles(Set.of(UserRole.CUSTOMER))
                        .email(newCustomer.getEmail())
                        .name(newCustomer.getName())
                        .surname(newCustomer.getSurname())
                        .build()
        );
    }

    public Company registerNewCompany(Company newCompany){
        return companyService.addCompany(
                Company.builder()
                        .username(newCompany.getUsername())
                        .password(passwordEncoder.encode(newCompany.getPassword()))
                        .avatar(newCompany.getAvatar())
                        .roles(Set.of(UserRole.COMPANY))
                        .cif(newCompany.getCif())
                        .companyName(newCompany.getCompanyName().replace(" ","_"))
                        .build()
        );
    }

}
