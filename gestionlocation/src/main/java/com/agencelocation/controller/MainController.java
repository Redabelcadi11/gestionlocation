package com.agencelocation.controller;

import com.agencelocation.model.Vehicule;
import com.agencelocation.repository.VehiculeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Controller
public class MainController {

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @GetMapping("/home")
    public String home(Model model) {
        // Charger la liste des véhicules
        List<Vehicule> vehicules = vehiculeRepository.findAll();
        model.addAttribute("vehicules", vehicules);

        // Ajouter l'information d'authentification au modèle
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        model.addAttribute("isAuthenticated", isAuthenticated);

        return "home"; // Le nom de la vue Thymeleaf
    }


    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";  // Redirige vers /home
    }

}