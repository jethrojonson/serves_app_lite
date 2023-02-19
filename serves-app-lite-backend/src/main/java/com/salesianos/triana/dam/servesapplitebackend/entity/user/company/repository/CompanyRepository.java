package com.salesianos.triana.dam.servesapplitebackend.entity.user.company.repository;

import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.model.Company;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

    @EntityGraph(value = "company-menu")
    List<Company> findAll();

    @EntityGraph(value = "company-menu", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Company> findById(UUID id);
}
