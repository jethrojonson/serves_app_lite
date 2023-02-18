package com.salesianos.triana.dam.servesapplitebackend.entity.user.company.repository;

import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
}
