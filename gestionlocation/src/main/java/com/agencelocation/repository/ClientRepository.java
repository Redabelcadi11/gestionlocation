package com.agencelocation.repository;

import com.agencelocation.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUsername(String username);  // Trouver un client par son nom d'utilisateur
    Optional<Client> findByEmail(String email);        // Trouver un client par son email
}
