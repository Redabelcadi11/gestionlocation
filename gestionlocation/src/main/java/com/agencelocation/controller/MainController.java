package com.agencelocation.controller;

import com.agencelocation.model.Vehicule;
import com.agencelocation.repository.VehiculeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @GetMapping("/home")
    public String home(Model model) {
        List<Vehicule> vehicules = vehiculeRepository.findAll();
        model.addAttribute("vehicules", vehicules);
        return "home";
    }

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";  // Redirige vers /home
    }

}