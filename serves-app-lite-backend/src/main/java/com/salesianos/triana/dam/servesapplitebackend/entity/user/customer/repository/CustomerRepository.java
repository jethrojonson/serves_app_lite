package com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.repository;

import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
