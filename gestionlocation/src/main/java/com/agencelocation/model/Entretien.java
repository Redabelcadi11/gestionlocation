package com.agencelocation.model;

import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
public class Entretien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeEntretien;
    private LocalDate dateEntretien;
    private Double cout;

    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    private Vehicule vehicule;

    // Getters et setters
}
