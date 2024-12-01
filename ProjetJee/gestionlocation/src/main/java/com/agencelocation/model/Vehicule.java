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

    // Getters and setters
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


    public void setImmatriculation(String immatriculation){ this.immatriculation = immatriculation;}
    public String getImmatriculation() {
        return immatriculation;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
