package com.agencelocation.repository;

import com.agencelocation.model.VehiculeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface VehiculeTypeRepository extends JpaRepository<VehiculeType, Long> {
    Optional<VehiculeTypeRepository> findByNom(String nom);
}
