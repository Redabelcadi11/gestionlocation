package com.agencelocation.controller;

import com.agencelocation.model.Vehicule;
import com.agencelocation.repository.VehiculeRepository;
import com.agencelocation.repository.VehiculeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Autowired
    private VehiculeTypeRepository vehiculeTypeRepository;

    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        // Ajouter un nouvel objet Vehicule et les catégories disponibles au modèle
        model.addAttribute("vehicule", new Vehicule());
        model.addAttribute("categories", vehiculeTypeRepository.findAll());
        return "admin";  // Page d'administration (vous avez déjà cette page)
    }

    @GetMapping("/admin/vehicule/ajouter")
    public String showAjouterVehiculeForm(Model model) {
        // Préparer le formulaire d'ajout de véhicule
        model.addAttribute("vehicule", new Vehicule());
        model.addAttribute("categories", vehiculeTypeRepository.findAll());
        return "ajoutVehicule";  // Vue pour ajouter un véhicule (ajoutVehicule.html)
    }
    @GetMapping("/admin/vehicule/afficher")
    public String showAfficherVehiculeForm(Model model) {
        // Préparer le formulaire d'ajout de véhicule
        model.addAttribute("vehicule", new Vehicule());
        model.addAttribute("categories", vehiculeTypeRepository.findAll());
        return "afficherVehicule";  // Vue pour ajouter un véhicule (ajoutVehicule.html)
    }

    @PostMapping("/admin/vehicule")
    public String addVehicule(@ModelAttribute Vehicule vehicule) {
        // Sauvegarder le véhicule dans la base de données
        vehiculeRepository.save(vehicule);
        return "redirect:/admin";  // Rediriger vers la page d'accueil après l'enregistrement
    }


}
