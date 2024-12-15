package com.agencelocation.repository;

import com.agencelocation.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    // Méthode pour trouver un véhicule par son immatriculation
    Optional<Vehicule> findByImmatriculation(String immatriculation);
}
