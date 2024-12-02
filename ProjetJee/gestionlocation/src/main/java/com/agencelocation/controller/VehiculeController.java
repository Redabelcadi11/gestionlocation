package com.agencelocation.controller;

import com.agencelocation.model.Vehicule;
import com.agencelocation.model.VehiculeType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class VehiculeController {

    private final List<Vehicule> vehicules;

    public VehiculeController() {
        // Initialisation des données factices
        Set<VehiculeType> categories1 = new HashSet<>();
        categories1.add(new VehiculeType("SUV"));
        categories1.add(new VehiculeType("Familiale"));

        Set<VehiculeType> categories2 = new HashSet<>();
        categories2.add(new VehiculeType("Sport"));

        Set<VehiculeType> categories3 = new HashSet<>();
        categories3.add(new VehiculeType("Compact"));

        this.vehicules = List.of(
                new Vehicule(1L, "Renault Clio", "AA-123-BB", "https://www.planeterenault.com/images/1200x0/UserFiles/photos/slideshow/Renault_Clio_RS_200-EDC_IMG_0250.jpg", categories3, 166.30),
                new Vehicule(2L, "Ford Mustang", "CC-456-DD", "https://m.sprint-racing.com/images/bandeau/voiture/MUS21-m.jpg", categories2, 203.22),
                new Vehicule(3L, "Tesla Model S", "DD-336-DS", "https://images.caradisiac.com/logos/7/0/0/8/277008/S8-les-tesla-model-s-et-x-augmentent-leurs-prix-202285.jpg", categories1, 425.00),
                new Vehicule(4L, "Audi RS7", "CAS-411-DX", "https://sf2.sportauto.fr/wp-content/uploads/sportauto/2023/07/audi-rs7-by-abt-2.jpg", categories2, 425.00)
        );
    }

    @GetMapping("/vehicules")
    public String afficherVehicules(Model model) {
        model.addAttribute("vehicules", vehicules);
        return "vehicules"; // Retourne la liste des véhicules
    }

    @GetMapping("/vehicules/{id}")
    public String afficherDetailsVehicule(@PathVariable Long id, Model model) {
        Vehicule vehicule = vehicules.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (vehicule == null) {
            model.addAttribute("errorMessage", "Véhicule non trouvé.");
            return "error"; // Créez une page d'erreur explicite
        }

        model.addAttribute("vehicule", vehicule);
        return "vehicule-details";
    }

}
