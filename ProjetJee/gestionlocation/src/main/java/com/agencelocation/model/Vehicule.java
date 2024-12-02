package com.agencelocation.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modele;
    private String immatriculation;
    private String imageUrl;

    @ManyToMany
    @JoinTable(
            name = "vehicule_categorie",
            joinColumns = @JoinColumn(name = "vehicule_id"),
            inverseJoinColumns = @JoinColumn(name = "categorie_id")
    )
    private Set<VehiculeType> categories;
    private double prix; // Nouvel attribut
    // Constructeur par défaut
    public Vehicule() {}

    // Constructeur personnalisé
    public Vehicule(Long id, String modele, String immatriculation, String imageUrl, Set<VehiculeType> categories, double prix) {
        this.id = id;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.imageUrl = imageUrl;
        this.categories = categories;
        this.prix = prix;
    }

    // Getters et setters
    public Set<VehiculeType> getCategories() {
        return categories;
    }

    public void setCategories(Set<VehiculeType> categories) {
        this.categories = categories;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getImmatriculation() {
        return immatriculation;
    }
    public Long getId() {
        return id;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
