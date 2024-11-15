package com.agencelocation.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class VehiculeType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @ManyToMany(mappedBy = "categories")
    private Set<Vehicule> vehicules;

    // Getters and setters
}
