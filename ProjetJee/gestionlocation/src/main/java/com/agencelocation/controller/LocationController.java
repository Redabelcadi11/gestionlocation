package com.agencelocation.controller;

import com.agencelocation.model.ContratLocation;
import com.agencelocation.model.Client;
import com.agencelocation.model.Vehicule;
import com.agencelocation.repository.ContratLocationRepository;
import com.agencelocation.repository.ClientRepository;
import com.agencelocation.repository.VehiculeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;
import java.util.Optional;

import java.time.LocalDate;
import java.util.List;

@Controller
public class LocationController {

    private static final Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private ContratLocationRepository contratLocationRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @GetMapping("/location/{vehiculeId}")
    public String afficherFormulaireLocation(@PathVariable Long vehiculeId, Principal principal, Model model) {
        // Vérifier si le principal est non null
        if (principal == null) {
            model.addAttribute("errorMessage", "Utilisateur non connecté.");
            return "error";
        }

        // Récupérer le nom d'utilisateur à partir du principal
        String username = principal.getName();  // getName() retourne le nom d'utilisateur

        // Trouver le client avec ce nom d'utilisateur
        Optional<Client> optionalClient = clientRepository.findByUsername(username);

        // Vérifier si le client existe
        if (optionalClient.isEmpty()) {
            model.addAttribute("errorMessage", "Client non trouvé.");
            return "error";
        }

        Client client = optionalClient.get();
        Long clientId = client.getId();

        // Récupérer le véhicule à partir de l'ID
        Optional<Vehicule> optionalVehicule = vehiculeRepository.findById(vehiculeId);
        if (optionalVehicule.isEmpty()) {
            model.addAttribute("errorMessage", "Véhicule non trouvé.");
            return "error";
        }

        Vehicule vehicule = optionalVehicule.get();

        // Passer les informations du véhicule et du client au modèle
        model.addAttribute("vehiculeId", vehiculeId);
        model.addAttribute("clientId", clientId);
        model.addAttribute("vehicule", vehicule);
        model.addAttribute("client", client);

        return "location";
    }

    // Traitement du formulaire de location
    @PostMapping("/ConfirmerLocation")
    public String louerVehicule(@RequestParam Long vehiculeId, @RequestParam Long clientId,
                                @RequestParam String dateDebut, @RequestParam String dateFin, Model model) {
        try {
            // Log début de la méthode
            logger.info("Début du traitement de la location pour le véhicule ID {} et client ID {}", vehiculeId, clientId);

            // Récupérer le véhicule et le client
            Vehicule vehicule = vehiculeRepository.findById(vehiculeId).orElse(null);
            Client client = clientRepository.findById(clientId).orElse(null);

            if (client == null) {
                logger.error("client introuvable pour les IDs client: {}", vehiculeId, clientId);
                model.addAttribute("errorMessage", "Véhicule ou client non trouvé.");
                return "error"; // Page d'erreur
            }

            // Log des objets récupérés
            logger.info("Véhicule trouvé : {}", vehicule);
            logger.info("Client trouvé : {}", client);

            // Calcul du montant
            LocalDate debut = LocalDate.parse(dateDebut);
            LocalDate fin = LocalDate.parse(dateFin);

            // Log des dates
            logger.info("Date de début : {}, Date de fin : {}", debut, fin);

            if (fin.isBefore(debut)) {
                logger.error("La date de fin est avant la date de début pour la location du véhicule avec l'ID {}", vehiculeId);
                model.addAttribute("errorMessage", "La date de fin ne peut pas être antérieure à la date de début.");
                return "error"; // Page d'erreur
            }

            long joursDeLocation = fin.toEpochDay() - debut.toEpochDay();

            // Log de la durée de location
            logger.info("Durée de la location en jours : {}", joursDeLocation);

            if (joursDeLocation <= 0) {
                logger.error("Durée de location invalide pour la location du véhicule avec l'ID {}", vehiculeId);
                model.addAttribute("errorMessage", "La durée de location doit être supérieure à zéro.");
                return "error"; // Page d'erreur
            }

            double montantTotal = vehicule.getPrix() * joursDeLocation;

            // Log du montant calculé
            logger.info("Montant total de la location : {}", montantTotal);

            ContratLocation contrat = new ContratLocation();
            contrat.setClient(client);
            contrat.setVehicule(vehicule);
            contrat.setDateDebut(debut);
            contrat.setDateFin(fin);
            contrat.setMontant(montantTotal);

            // Log avant l'enregistrement du contrat
            logger.info("Enregistrement du contrat de location pour le véhicule ID {} et client ID {}", vehiculeId, clientId);

            contratLocationRepository.save(contrat);

            // Log après l'enregistrement du contrat
            logger.info("Contrat de location créé avec succès pour le véhicule ID {} et client ID {}", vehiculeId, clientId);

            model.addAttribute("message", "Véhicule réservé avec succès !");
            return "profil";
        } catch (Exception e) {
            logger.error("Erreur lors de la location du véhicule", e);
            model.addAttribute("errorMessage", "Une erreur est survenue lors de la location.");
            return "error"; // Page d'erreur
        }
    }

}
