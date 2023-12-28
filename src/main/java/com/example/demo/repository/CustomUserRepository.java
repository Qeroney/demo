package com.example.demo.repository;

import com.example.demo.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, UUID> {
    Optional<CustomUser> findByEmail(String username);
}
