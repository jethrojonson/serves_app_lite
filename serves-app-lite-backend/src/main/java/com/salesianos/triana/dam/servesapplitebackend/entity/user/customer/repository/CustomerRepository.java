package com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.repository;

import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.model.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {


    @EntityGraph(value = "customer-orders")
    List<Customer> findAll();

    @EntityGraph(value = "customer-orders", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Customer> findById(UUID id);


}
