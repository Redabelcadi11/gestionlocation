package com.agencelocation.controller;

import com.agencelocation.model.Client;
import com.agencelocation.repository.ClientRepository;
import com.agencelocation.model.Vehicule;
import com.agencelocation.repository.VehiculeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/home")
    public String home(Model model) {
        List<Vehicule> vehicules = vehiculeRepository.findAll();
        model.addAttribute("vehicules", vehicules);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Vérifie si l'utilisateur est connecté
        boolean isConnected = authentication != null && authentication.isAuthenticated() &&
                !(authentication.getPrincipal() instanceof String &&
                        authentication.getPrincipal().equals("anonymousUser"));

        model.addAttribute("isConnected", isConnected);

        // Ajoute les informations du client si connecté
        if (isConnected) {
            String username = authentication.getName();
            Optional<Client> clientOpt = clientRepository.findByUsername(username);
            clientOpt.ifPresent(client -> model.addAttribute("client", client));
        }

        return "home";
    }


    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }
}
