package com.agencelocation.controller;

import com.agencelocation.model.Client;
import com.agencelocation.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private ClientRepository clientRepository;

    // Page de connexion
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("error", null); // Initialiser l'attribut error (optionnel)
        return "login"; // Assurez-vous que login.html existe dans /templates
    }

    // Page d'inscription
    @GetMapping("/register")
    public String registerPage() {
        return "register"; // Assurez-vous que register.html existe dans /templates
    }

    // Traitement de l'inscription
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String email,
                           @RequestParam String numeroTelephone,
                           Model model) {

        // Vérification de l'existence d'un utilisateur avec le même nom d'utilisateur
        Optional<Client> existingClient = clientRepository.findByUsername(username);
        if (existingClient.isPresent()) {
            model.addAttribute("error", "Nom d'utilisateur déjà utilisé");
            return "register"; // Reste sur la page d'inscription avec un message d'erreur
        }

        // Création d'un nouvel utilisateur
        Client newClient = new Client();
        newClient.setUsername(username);
        newClient.setPassword(password); // Pensez à encoder le mot de passe avant de le sauvegarder
        newClient.setEmail(email);
        newClient.setNumeroTelephone(numeroTelephone);
        newClient.setRole(Client.Role.ROLE_CLIENT);

        // Sauvegarde dans la base de données
        clientRepository.save(newClient);

        return "redirect:/login"; // Redirection vers la page de connexion
    }

    // (Optionnel) Gestion des erreurs lors de la connexion
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              Model model) {

        Optional<Client> clientOpt = clientRepository.findByUsername(username);
        if (clientOpt.isPresent() && clientOpt.get().getPassword().equals(password)) {
            // Ajouter des conditions pour rediriger selon le rôle
            if (clientOpt.get().getRole() == Client.Role.ROLE_ADMIN) {
                return "redirect:/admin";
            } else {
                return "redirect:/home";
            }
        }

        model.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
        return "login"; // Reste sur la page de connexion avec un message d'erreur
    }
}
