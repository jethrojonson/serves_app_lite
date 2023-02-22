package com.salesianos.triana.dam.servesapplitebackend.entity.user.base.service;

import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.model.User;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.model.roles.UserRole;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.repository.UserRepository;
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

}
