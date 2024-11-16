// AuthController.java

package com.agencelocation.controller;

import com.agencelocation.model.Client;
import com.agencelocation.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Assure-toi que login.html existe
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        Client client = clientRepository.findByUsername(username);

        if (client != null && client.getPassword().equals(password)) {
            if (client.getRole() == Client.Role.ADMIN) {
                return "redirect:/admin-dashboard"; // Page spécifique admin
            } else {
                return "redirect:/home"; // Redirection vers la page principale client
            }
        } else {
            model.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
            return "login"; // Retourne à la page de connexion
        }
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register"; // Page d'inscription
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam int numeroTelephone) {
        Client newClient = new Client();
        newClient.setUsername(username);
        newClient.setPassword(password);
        newClient.setEmail(email);
        newClient.setNumeroTelephone(numeroTelephone);
        newClient.setRole(Client.Role.CLIENT);
        clientRepository.save(newClient);
        return "redirect:/login";
    }
}
