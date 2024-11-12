package com.agencelocation.model;

import jakarta.persistence.*;

@Entity
public class Assurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroContrat;
    private String compagnie;
    private String couverture;
    private String dateExpiration;

    @OneToOne
    @JoinColumn(name = "vehicule_id")
    private Vehicule vehicule;

    // Getters et setters
}
