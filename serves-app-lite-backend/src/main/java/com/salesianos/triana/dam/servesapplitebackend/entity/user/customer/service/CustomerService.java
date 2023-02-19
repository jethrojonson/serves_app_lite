package com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.service;

import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.dto.CustomerDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.model.Customer;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerDTO addNewCustomer(CustomerDTO newCustomer){
        return CustomerDTO.of(customerRepository.save(CustomerDTO.of(newCustomer)));
    }

    public List<CustomerDTO> getAllCustomers(){
        return customerRepository.findAll().stream().map(CustomerDTO::of).toList();
    }

    public CustomerDTO getCustomerById(UUID id){
        Customer result = customerRepository.findById(id).get();
        return CustomerDTO.of(result);
    }

    @Transactional
    public CustomerDTO updateACustomer(CustomerDTO toUpdate, UUID id){
        Customer result = customerRepository.findById(id).get();
        result.setName(toUpdate.getName());
        result.setSurname(toUpdate.getSurname());
        return CustomerDTO.of(customerRepository.save(result));
    }

    public void removeACustomer(UUID id){
        customerRepository.delete(customerRepository.findById(id).get());
    }
}
