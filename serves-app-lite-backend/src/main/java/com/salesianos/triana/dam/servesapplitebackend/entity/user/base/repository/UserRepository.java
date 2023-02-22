package com.salesianos.triana.dam.servesapplitebackend.entity.user.base.repository;

import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findFirstByUsername(String username);
}
