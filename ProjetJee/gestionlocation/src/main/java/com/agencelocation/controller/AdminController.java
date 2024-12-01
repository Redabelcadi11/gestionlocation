package com.agencelocation.controller;

import com.agencelocation.model.Vehicule;
import com.agencelocation.repository.VehiculeRepository;
import com.agencelocation.repository.VehiculeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


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
        return "admin";
    }

    @PostMapping("/admin/vehicule")
    public String addVehicule(@ModelAttribute Vehicule vehicule) {
        // Sauvegarder directement l'objet véhicule avec ses catégories
        vehiculeRepository.save(vehicule);
        return "redirect:/home"; // Rediriger vers la page principale après l'enregistrement
    }
}
