package com.agencelocation.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;
    private String numeroTelephone;
    private String permisConduire;


    @OneToMany(mappedBy = "client")
    private List<ContratLocation> contrats;

    // Getters and setters
}