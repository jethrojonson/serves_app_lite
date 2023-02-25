package com.salesianos.triana.dam.servesapplitebackend.entity.user.company.repository;

import com.salesianos.triana.dam.servesapplitebackend.entity.item.model.Item;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.model.Order;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.model.Product;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID>, JpaSpecificationExecutor<Company> {

    Optional<Company> findFirstByUsername(String username);

    Optional<Company> findFirstByCompanyName(String companyName);

    @EntityGraph(value = "company-menu", type = EntityGraph.EntityGraphType.LOAD)
    List<Company> findAll();

    @EntityGraph(value = "company-menu", type = EntityGraph.EntityGraphType.LOAD)
    Page<Company> findAll(Specification<Company> s, Pageable p);

    @EntityGraph(value = "company-menu", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Company> findById(UUID id);

    @Query("""
            SELECT DISTINCT i
            FROM Item i
            LEFT JOIN FETCH i.company c
            WHERE c.id = :id
            """)
    List<Item> loadMenuItems(UUID id);

    @Query("""
            SELECT DISTINCT o
            FROM Order o
            LEFT JOIN FETCH o.company c
            WHERE c.id = :id
            """)
    List<Order> loadOrdersReceived(UUID id);


    @Query("""
            SELECT p
            FROM Product p
            WHERE p.id = :id
            """)
    Optional<Product> findProductById(Long id);

    @Query("""
            SELECT i
            FROM Item i
            WHERE i.id = :id
            """)
    Optional<Item> findItemById(Long id);


}
