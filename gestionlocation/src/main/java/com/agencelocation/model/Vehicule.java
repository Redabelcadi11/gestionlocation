package com.agencelocation.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String modele;
    private String marque;
    private String numeroImmatriculation;
    private String type;
    private Double tarifJournalier;
    private boolean disponibilite;

    @OneToOne(mappedBy = "vehicule", cascade = CascadeType.ALL)
    private Assurance assurance;

    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL)
    private List<Entretien> entretiens;

    // Getters et setters
}
