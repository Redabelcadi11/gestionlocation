package com.agencelocation.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class VehiculeType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom;

    @ManyToMany(mappedBy = "categories")
    private Set<Vehicule> vehicules;

    // Constructeur par défaut
    public VehiculeType() {}

    // Constructeur personnalisé
    public VehiculeType(String nom) {
        this.nom = nom;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Vehicule> getVehicules() {
        return vehicules;
    }

    public void setVehicules(Set<Vehicule> vehicules) {
        this.vehicules = vehicules;
    }

    @Override
    public String toString() {
        return "VehiculeType{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
