package com.agencelocation.repository;

import com.agencelocation.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}
