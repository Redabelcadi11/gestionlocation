package com.agencelocation.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String adresse;
    private String numeroTelephone;
    private String permisConduire;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<ContratLocation> contrats;

    // Getters et setters
}
