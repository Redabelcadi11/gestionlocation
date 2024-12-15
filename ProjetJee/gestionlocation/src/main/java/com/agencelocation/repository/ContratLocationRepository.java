package com.agencelocation.repository;

import com.agencelocation.model.ContratLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContratLocationRepository extends JpaRepository<ContratLocation, Long> {
    List<ContratLocation> findByDateFinAfter(LocalDate date); // Contrats actifs
    List<ContratLocation> findByClientId(Long clientId);
}
