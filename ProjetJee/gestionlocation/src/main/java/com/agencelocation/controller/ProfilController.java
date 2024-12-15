package com.agencelocation.controller;

import com.agencelocation.model.Client;
import com.agencelocation.model.ContratLocation;
import com.agencelocation.repository.ClientRepository;
import com.agencelocation.repository.ContratLocationRepository;
import com.agencelocation.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ProfilController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ContratLocationRepository contratLocationRepository;

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @GetMapping("/profil")
    public String showProfile(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("username", username);

        // Trouver le client par username
        Client client = clientRepository.findByUsername(username).orElse(null);

        if (client != null) {
            // Récupérer les contrats de location pour ce client
            List<ContratLocation> contrats = contratLocationRepository.findByClientId(client.getId());
            model.addAttribute("contrats", contrats);
        }

        return "profil";
    }
}
