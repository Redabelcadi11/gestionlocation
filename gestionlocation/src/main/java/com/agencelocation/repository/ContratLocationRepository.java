package com.agencelocation.repository;

import com.agencelocation.model.ContratLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratLocationRepository extends JpaRepository<ContratLocation, Long> {
    // Ajoute des méthodes de requêtes personnalisées si nécessaire
}