package com.agencelocation.repository;

import com.agencelocation.model.Entretien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntretienRepository extends JpaRepository<Entretien, Long> {
    // Ajoute des méthodes de requêtes personnalisées si nécessaire
}
