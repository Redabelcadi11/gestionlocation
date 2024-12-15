package com.agencelocation.controller;

import com.agencelocation.model.ContratLocation;
import com.agencelocation.model.Vehicule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller

public class LocationController {

    private final List<Vehicule> vehicules;

    @Autowired
    public LocationController() {
        // Initialiser des véhicules pour la démonstration
        this.vehicules = new ArrayList<>();
        vehicules.add(new Vehicule(1L, "Tesla Model S", "AA-123-BB", "https://example.com/tesla.jpg", null, 200.0));
        vehicules.add(new Vehicule(2L, "Renault Clio", "CC-456-DD", "https://example.com/clio.jpg", null, 50.0));
    }

    // Afficher le formulaire de location
    @GetMapping("/location/{id}")
    public String afficherFormulaireLocation(@PathVariable Long id, Model model) {
        Vehicule vehicule = vehicules.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (vehicule == null) {
            model.addAttribute("errorMessage", "Le véhicule demandé est introuvable.");
            return "error";
        }

        model.addAttribute("vehicule", vehicule);
        return "location";
    }

    @PostMapping("/confirmerLocation")
    public String confirmerLocation(@RequestParam("locationId") Long locationId, Model model) {
        // Logique pour confirmer la location (par exemple, mise à jour dans la base de données)
        // locationService.confirmerLocation(locationId);

        // Ajouter un message de confirmation au modèle
        model.addAttribute("message", "Votre location a été confirmée avec succès !");
        return "confirmation-location"; // Nom de la vue (HTML)
    }

    // Page d'erreur personnalisée
    @GetMapping("/error")
    public String handleError(Model model) {
        model.addAttribute("errorMessage", "Une erreur est survenue.");
        return "error";
    }
}
