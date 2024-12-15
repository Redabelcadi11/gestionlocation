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
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Optional<Client> clientOpt = clientRepository.findByUsername(username);
            if (clientOpt.isPresent()) {
                Client client = clientOpt.get();
                model.addAttribute("isConnected", client.isEstConnecte());
                model.addAttribute("client", client);
            } else {
                model.addAttribute("isConnected", false);
            }
        } else {
            model.addAttribute("isConnected", false);
        }

        return "home";
    }


    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }
}
