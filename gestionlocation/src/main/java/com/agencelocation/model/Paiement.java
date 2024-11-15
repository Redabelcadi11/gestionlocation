package com.agencelocation.model;

import jakarta.persistence.*;

@Entity
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double montant;

    @OneToOne
    @JoinColumn(name = "contrat_id")
    private ContratLocation contratLocation;

    // Getters and setters
}
