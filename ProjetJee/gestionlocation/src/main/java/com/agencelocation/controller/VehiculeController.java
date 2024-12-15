package com.agencelocation.controller;

import com.agencelocation.model.Vehicule;
import com.agencelocation.model.VehiculeType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
                new Vehicule(3L, "Tesla Model S", "DD-336-DS", "https://www.mgprestigecar.fr/wp-content/uploads/2024/08/Tesla-Model-S-updates-2025-1024x585.jpg", categories1, 425.00),
                new Vehicule(4L, "Audi RS7", "CAS-411-DX", "https://sf2.sportauto.fr/wp-content/uploads/sportauto/2023/07/audi-rs7-by-abt-2.jpg", categories2, 425.00),
                new Vehicule(1L, "Renault Clio", "AA-123-BB", "https://www.planeterenault.com/images/1200x0/UserFiles/photos/slideshow/Renault_Clio_RS_200-EDC_IMG_0250.jpg", categories3, 166.30),
                new Vehicule(2L, "Ford Mustang", "CC-456-DD", "https://m.sprint-racing.com/images/bandeau/voiture/MUS21-m.jpg", categories2, 203.22),
                new Vehicule(3L, "Tesla Model S", "DD-336-DS", "https://www.mgprestigecar.fr/wp-content/uploads/2024/08/Tesla-Model-S-updates-2025-1024x585.jpg", categories1, 425.00),
                new Vehicule(4L, "Audi RS7", "CAS-411-DX", "https://sf2.sportauto.fr/wp-content/uploads/sportauto/2023/07/audi-rs7-by-abt-2.jpg", categories2, 425.00),
                new Vehicule(1L, "Renault Clio", "AA-123-BB", "https://www.planeterenault.com/images/1200x0/UserFiles/photos/slideshow/Renault_Clio_RS_200-EDC_IMG_0250.jpg", categories3, 166.30),
                new Vehicule(2L, "Ford Mustang", "CC-456-DD", "https://m.sprint-racing.com/images/bandeau/voiture/MUS21-m.jpg", categories2, 203.22),
                new Vehicule(3L, "Tesla Model S", "DD-336-DS", "https://www.mgprestigecar.fr/wp-content/uploads/2024/08/Tesla-Model-S-updates-2025-1024x585.jpg", categories1, 425.00),
                new Vehicule(4L, "Audi RS7", "CAS-411-DX", "https://sf2.sportauto.fr/wp-content/uploads/sportauto/2023/07/audi-rs7-by-abt-2.jpg", categories2, 425.00)
        );
    }

    @GetMapping("/vehicules")
    public String afficherVehicules(
            @RequestParam(required = false) String categorie,
            @RequestParam(required = false) String modele,
            Model model) {

        // Filtrage des véhicules en fonction des critères
        List<Vehicule> filtresVehicules = vehicules;

        if (categorie != null && !categorie.isEmpty()) {
            filtresVehicules = filtresVehicules.stream()
                    .filter(v -> v.getCategories().stream()
                            .anyMatch(c -> c.getNom().equalsIgnoreCase(categorie)))
                    .collect(Collectors.toList());
        }

        if (modele != null && !modele.isEmpty()) {
            filtresVehicules = filtresVehicules.stream()
                    .filter(v -> v.getModele().toLowerCase().contains(modele.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Ajouter les résultats filtrés au modèle
        model.addAttribute("vehicules", filtresVehicules);

        // Ajouter toutes les catégories pour le formulaire de filtre
        Set<String> toutesCategories = vehicules.stream()
                .flatMap(v -> v.getCategories().stream().map(VehiculeType::getNom))
                .collect(Collectors.toSet());
        model.addAttribute("categories", toutesCategories);

        return "vehicules"; // Vue de la liste des véhicules
    }

    @GetMapping("/vehicules/{id}")
    public String afficherDetailsVehicule(@PathVariable Long id, Model model) {
        Vehicule vehicule = vehicules.stream()
                .filter(v -> v.getId() != null && v.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (vehicule == null || vehicule.getPrix() == 0) {
            model.addAttribute("errorMessage", "Le véhicule n'existe pas ou son prix est manquant.");
            return "error"; // Page d'erreur personnalisée
        }

        model.addAttribute("vehicule", vehicule);
        return "vehicule-details"; // Vue des détails du véhicule
    }

}
