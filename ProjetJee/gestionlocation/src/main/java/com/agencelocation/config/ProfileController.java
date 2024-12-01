package com.agencelocation.controller;

import com.agencelocation.model.Client;
import com.agencelocation.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    private ClientRepository clientRepository;

    // Affichage de la page profil
    @GetMapping("/profile")
    public String showProfile(Model model) {
        // Récupérer l'utilisateur connecté
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Charger les informations du client depuis la base de données
        Optional<Client> optionalClient = clientRepository.findByUsername(username);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            model.addAttribute("client", client);  // Ajouter les informations du client au modèle
            model.addAttribute("role", client.getRole());  // Ajouter le rôle au modèle
            return "profile";  // Retourne le fichier profile.html
        } else {
            return "redirect:/login";  // Redirige vers la page de connexion si le client n'est pas trouvé
        }
    }

    // Mise à jour des informations du profil
    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute Client updatedClient, Model model) {
        // Récupérer l'utilisateur connecté
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Récupérer les informations existantes
        Optional<Client> optionalClient = clientRepository.findByUsername(username);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setEmail(updatedClient.getEmail());
            client.setNumeroTelephone(updatedClient.getNumeroTelephone());
            client.setPermisConduire(updatedClient.getPermisConduire());
            clientRepository.save(client);

            model.addAttribute("client", client);  // Mettre à jour les informations du client
            model.addAttribute("successMessage", "Profil mis à jour avec succès !");
        }
        return "profile";  // Retourne la page profile.html
    }
}
