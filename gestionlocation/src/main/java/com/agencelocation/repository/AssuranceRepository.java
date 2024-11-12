package com.agencelocation.repository;

import com.agencelocation.model.Assurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuranceRepository extends JpaRepository<Assurance, Long> {
    // Ajoute des méthodes de requêtes personnalisées si nécessaire
}
