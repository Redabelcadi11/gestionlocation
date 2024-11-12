package com.agencelocation.model;

import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
public class ContratLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String statut;
    private Double coutTotal;

    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    private Vehicule vehicule;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    // Getters et setters
}
