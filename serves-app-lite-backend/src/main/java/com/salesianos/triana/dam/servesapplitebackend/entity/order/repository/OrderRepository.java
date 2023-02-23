package com.salesianos.triana.dam.servesapplitebackend.entity.order.repository;

import com.salesianos.triana.dam.servesapplitebackend.entity.order.model.Order;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.model.Product;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.model.Company;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.model.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(value = "order-lines", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Order> findById(Long id);

    @Query("""
            SELECT c
            FROM Customer c
            WHERE c.id = ?1
            """)
    @EntityGraph(value = "customer-orders", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Customer> findCustomerById(UUID id);

    @Query("""
            SELECT c
            FROM Company c
            WHERE c.companyName = ?1
            """)
    @EntityGraph(value = "company-orders", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Company> findCompanyByCompanyName(String companyName);

    @Query("""
            SELECT p
            FROM Product p
            WHERE p.id = ?1
            """)
    Optional<Product> findProductById(Long id);

}
