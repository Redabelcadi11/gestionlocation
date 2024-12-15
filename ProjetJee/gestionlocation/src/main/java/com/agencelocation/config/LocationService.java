package com.agencelocation.config;

import com.agencelocation.model.ContratLocation;
import com.agencelocation.model.Vehicule;
import com.agencelocation.model.Client;
import com.agencelocation.repository.ContratLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.Principal;

import java.time.LocalDate;

@Service
public class LocationService {

    @Autowired
    private ContratLocationRepository contratLocationRepository;

    public ContratLocation creerContrat(Vehicule vehicule, Client client, LocalDate dateDebut, LocalDate dateFin, Double montant) {
        ContratLocation contrat = new ContratLocation();
        contrat.setVehicule(vehicule);
        contrat.setClient(client);
        contrat.setDateDebut(dateDebut);
        contrat.setDateFin(dateFin);
        contrat.setMontant(montant);

        return contratLocationRepository.save(contrat);
    }
}
