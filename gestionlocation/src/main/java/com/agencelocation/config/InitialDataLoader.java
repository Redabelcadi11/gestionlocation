package com.agencelocation.config;

import com.agencelocation.model.Client;
import com.agencelocation.model.VehiculeType;
import com.agencelocation.repository.ClientRepository;
import com.agencelocation.repository.VehiculeTypeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialDataLoader {

    private final ClientRepository clientRepository;
    private final VehiculeTypeRepository vehiculeTypeRepository;

    @Value("${admin.username}")
    private String adminUsername;
    @Value("${admin.password}")
    private String adminPassword;
    @Value("${admin.role}")
    private String adminRole;

    // Constructeur avec injection de dépendances
    public InitialDataLoader(ClientRepository clientRepository, VehiculeTypeRepository vehiculeTypeRepository) {
        this.clientRepository = clientRepository;
        this.vehiculeTypeRepository = vehiculeTypeRepository;
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

            createCategorieVehiculeIfNotExist("BMW");
            createCategorieVehiculeIfNotExist("Renault");
            createCategorieVehiculeIfNotExist("Audi");
            createCategorieVehiculeIfNotExist("Opel");
            createCategorieVehiculeIfNotExist("Quatre-quatre");
            createCategorieVehiculeIfNotExist("SUV");
            createCategorieVehiculeIfNotExist("Citadine");
            createCategorieVehiculeIfNotExist("4 roues motrices");
            createCategorieVehiculeIfNotExist("Moto");
        };
    }

    private void createCategorieVehiculeIfNotExist(String categorieNom) {
        if (!vehiculeTypeRepository.findByNom(categorieNom).isPresent()) {
            VehiculeType categorie = new VehiculeType(categorieNom);
            vehiculeTypeRepository.save(categorie);
        }
    }
}
