package com.agencelocation.controller;

import com.agencelocation.model.Client;
import com.agencelocation.model.ContratLocation;
import com.agencelocation.repository.ClientRepository;
import com.agencelocation.repository.ContratLocationRepository;
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

    @GetMapping("/profil")
    public String showProfile(Model model, Principal principal) {
        // Récupérer le nom d'utilisateur du client connecté
        String username = principal.getName();

        // Trouver le client par username
        Client client = clientRepository.findByUsername(username).orElse(null);

        if (client == null) {
            // Si le client n'est pas trouvé, rediriger vers la page de connexion
            return "redirect:/login";
        }

        // Ajouter les informations du client au modèle
        model.addAttribute("client", client);

        // Récupérer les contrats associés à ce client
        List<ContratLocation> contrats = contratLocationRepository.findByClientId(client.getId());
        model.addAttribute("contrats", contrats);

        return "profil"; // Vue profil.html
    }
}
