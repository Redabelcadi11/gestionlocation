package com.agencelocation.config;

import com.agencelocation.model.Client;
import com.agencelocation.model.Vehicule;
import com.agencelocation.model.VehiculeType;
import com.agencelocation.repository.ClientRepository;
import com.agencelocation.repository.VehiculeRepository;
import com.agencelocation.repository.VehiculeTypeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Optional;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class InitialDataLoader {

    private final ClientRepository clientRepository;
    private final VehiculeTypeRepository vehiculeTypeRepository;
    private final VehiculeRepository vehiculeRepository;

    @Value("${admin.username}")
    private String adminUsername;
    @Value("${admin.password}")
    private String adminPassword;
    @Value("${admin.role}")
    private String adminRole;

    // Constructeur avec injection de dépendances
    public InitialDataLoader(ClientRepository clientRepository, VehiculeTypeRepository vehiculeTypeRepository, VehiculeRepository vehiculeRepository) {
        this.clientRepository = clientRepository;
        this.vehiculeTypeRepository = vehiculeTypeRepository;
        this.vehiculeRepository = vehiculeRepository;
    }

    @Bean
    public CommandLineRunner dataLoader() {
        return args -> {
            // Création de l'administrateur si non existant
            if (!clientRepository.findByUsername(adminUsername).isPresent()) {
                Client admin = new Client();
                admin.setUsername(adminUsername);
                admin.setPassword(adminPassword);
                admin.setRole(Client.Role.ROLE_ADMIN);
                clientRepository.save(admin);
                System.out.println("Compte admin créé avec succès !");
            }

            // Création des catégories de véhicules si non existantes
            createCategorieVehiculeIfNotExist("SUV");
            createCategorieVehiculeIfNotExist("Familiale");
            createCategorieVehiculeIfNotExist("Sport");
            createCategorieVehiculeIfNotExist("Compact");

            // Création des véhicules avec leurs catégories
            createVehiculeIfNotExist("Renault Clio", "AA-123-BB", "https://www.planeterenault.com/images/1200x0/UserFiles/photos/slideshow/Renault_Clio_RS_200-EDC_IMG_0250.jpg", "Compact", 166.30);
            createVehiculeIfNotExist("Ford Mustang", "CC-456-DD", "https://m.sprint-racing.com/images/bandeau/voiture/MUS21-m.jpg", "Sport", 203.22);
            createVehiculeIfNotExist("Tesla Model S", "DD-336-DS", "https://www.mgprestigecar.fr/wp-content/uploads/2024/08/Tesla-Model-S-updates-2025-1024x585.jpg", "SUV", 425.00);
            createVehiculeIfNotExist("Audi RS7", "CAS-411-DX", "https://sf2.sportauto.fr/wp-content/uploads/sportauto/2023/07/audi-rs7-by-abt-2.jpg", "Sport", 425.00);
            createVehiculeIfNotExist("Renault Clio", "AA-173-BB", "https://www.planeterenault.com/images/1200x0/UserFiles/photos/slideshow/Renault_Clio_RS_200-EDC_IMG_0250.jpg", "Compact", 166.30);
            createVehiculeIfNotExist("Ford Mustang", "CC-756-DD", "https://m.sprint-racing.com/images/bandeau/voiture/MUS21-m.jpg", "Sport", 203.22);
            createVehiculeIfNotExist("Tesla Model S", "DD-376-DS", "https://www.mgprestigecar.fr/wp-content/uploads/2024/08/Tesla-Model-S-updates-2025-1024x585.jpg", "SUV", 425.00);
            createVehiculeIfNotExist("Audi RS7", "CAS-411-D7", "https://sf2.sportauto.fr/wp-content/uploads/sportauto/2023/07/audi-rs7-by-abt-2.jpg", "Sport", 425.00);
        };
    }

    private void createCategorieVehiculeIfNotExist(String categorieNom) {
        if (!vehiculeTypeRepository.findByNom(categorieNom).isPresent()) {
            VehiculeType categorie = new VehiculeType(categorieNom);
            vehiculeTypeRepository.save(categorie);
        }
    }

    private void createVehiculeIfNotExist(String modele, String immatriculation, String imageUrl, String categorieNom, double prix) {
        if (!vehiculeRepository.findByImmatriculation(immatriculation).isPresent()) {
            // Recherche de la catégorie par son nom
            Optional<VehiculeType> optionalCategorie = vehiculeTypeRepository.findByNom(categorieNom);

            // Vérification si la catégorie est présente
            if (optionalCategorie.isPresent()) {
                VehiculeType categorie = optionalCategorie.get();  // Récupère le VehiculeType trouvé

                // Créez un ensemble pour stocker la catégorie du véhicule
                Set<VehiculeType> categories = new HashSet<>();
                categories.add(categorie);

                // Création du véhicule avec les paramètres nécessaires
                Vehicule vehicule = new Vehicule();
                vehicule.setImmatriculation(immatriculation);
                vehicule.setModele(modele);
                vehicule.setCategories(categories);
                vehicule.setPrix(prix);
                vehicule.setImageUrl(imageUrl);

                // Sauvegarde du véhicule dans la base de données
                vehiculeRepository.save(vehicule);

                System.out.println("Véhicule ajouté avec succès !");
            } else {
                // La catégorie n'a pas été trouvée
                System.out.println("Catégorie " + categorieNom + " non trouvée.");
            }
        } else {
            // Le véhicule existe déjà avec cette immatriculation
            System.out.println("Un véhicule avec cette immatriculation existe déjà.");
        }

    }
}
