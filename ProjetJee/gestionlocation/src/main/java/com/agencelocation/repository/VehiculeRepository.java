package com.agencelocation.repository;

import com.agencelocation.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    // Ajoute des méthodes de requêtes personnalisées si nécessaire
}
