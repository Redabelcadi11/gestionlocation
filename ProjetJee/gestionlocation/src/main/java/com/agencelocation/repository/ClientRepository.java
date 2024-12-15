package com.agencelocation.repository;

import com.agencelocation.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUsername(String username);  // Trouver un client par son nom d'utilisateur
    Optional<Client> findByEmail(String email);        // Trouver un client par son email
    boolean existsByEstConnecteTrue(); // Méthode pour vérifier si quelqu’un est connecté

}
