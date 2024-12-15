package com.agencelocation.controller;
import com.agencelocation.model.Vehicule;
import com.agencelocation.repository.VehiculeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class ProfilController {

    @GetMapping("/profil")
    public String afficherProfil(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        // Ajouter d'autres attributs pour afficher les informations de l'utilisateur
        return "profil";
    }
}
