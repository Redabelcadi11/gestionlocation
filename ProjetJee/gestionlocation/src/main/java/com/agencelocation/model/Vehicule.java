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

    @Column(nullable = false)
    private boolean disponible = true;

    @ManyToMany
    @JoinTable(
            name = "vehicule_categorie",
            joinColumns = @JoinColumn(name = "vehicule_id"),
            inverseJoinColumns = @JoinColumn(name = "categorie_id")
    )
    private Set<VehiculeType> categories;

    private double prix; // Prix par jour du véhicule

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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Set<VehiculeType> getCategories() {
        return categories;
    }

    public void setCategories(Set<VehiculeType> categories) {
        this.categories = categories;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "id=" + id +
                ", modele='" + modele + '\'' +
                ", immatriculation='" + immatriculation + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", disponible=" + disponible +
                ", categories=" + categories +
                ", prix=" + prix +
                '}';
    }
}
