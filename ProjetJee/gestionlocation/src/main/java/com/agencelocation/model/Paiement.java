package com.agencelocation.model;

import jakarta.persistence.*;

@Entity
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double montant;

    @OneToOne
    @JoinColumn(name = "contrat_location_id")
    private ContratLocation contratLocation;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public ContratLocation getContratLocation() {
        return contratLocation;
    }

    public void setContratLocation(ContratLocation contratLocation) {
        this.contratLocation = contratLocation;
    }
}
