package com.agencelocation.controller;

import com.agencelocation.model.Client;
import com.agencelocation.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private ClientRepository clientRepository;

    // Page de connexion
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("error", null);
        return "login";
    }

    // Page d'inscription
    @GetMapping("/register")
    public String registerPage() {
        return "register";
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
        newClient.setPassword(password);
        newClient.setEmail(email);
        newClient.setNumeroTelephone(numeroTelephone);
        newClient.setRole(Client.Role.ROLE_CLIENT);
        newClient.setEstConnecte(false); // Par défaut, l'utilisateur n'est pas connecté

        // Sauvegarde dans la base de données
        clientRepository.save(newClient);

        return "redirect:/login"; // Redirection vers la page de connexion
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              Model model) {
        // Log pour vérifier que la méthode est bien appelée
        System.out.println("Tentative de connexion avec username: " + username);

        Optional<Client> clientOpt = clientRepository.findByUsername(username);

        if (clientOpt.isPresent() && clientOpt.get().getPassword().equals(password)) {
            Client client = clientOpt.get();
            client.setEstConnecte(true); // Mise à jour du champ
            clientRepository.save(client); // Sauvegarde dans la base de données

            // Vérification de la redirection
            if (username.equals("admin")) {
                System.out.println("Redirection vers admin");
                return "redirect:/admin"; // Redirige vers la page admin
            } else {
                System.out.println("Redirection vers home");
                return "home"; // Redirige vers la page home
            }
        } else {
            // Si les informations de connexion sont incorrectes
            System.out.println("Nom d'utilisateur ou mot de passe incorrect");
            model.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
            return "login"; // Retourne la page de connexion avec message d'erreur
        }
    }




    // Gestion de la déconnexion
    @GetMapping("/logout")
    public String logout(@RequestParam String username) {
        Optional<Client> clientOpt = clientRepository.findByUsername(username);

        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            client.setEstConnecte(false); // Déconnexion
            clientRepository.save(client);
        }

        return "redirect:/login";
    }

}
