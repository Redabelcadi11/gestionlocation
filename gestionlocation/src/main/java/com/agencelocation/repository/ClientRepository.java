package com.agencelocation.repository;

import com.agencelocation.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Ajoute des méthodes de requêtes personnalisées si nécessaire
}
