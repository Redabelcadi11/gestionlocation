package com.agencelocation.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marque;
    private String modele;
    private String immatriculation;

    @ManyToMany
    @JoinTable(
            name = "vehicule_categorie",
            joinColumns = @JoinColumn(name = "vehicule_id"),
            inverseJoinColumns = @JoinColumn(name = "categorie_id")
    )
    private Set<VehiculeType> categories;

    // Getters and setters
}
