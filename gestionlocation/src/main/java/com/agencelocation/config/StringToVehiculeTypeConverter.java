package com.agencelocation.config;

import com.agencelocation.model.VehiculeType;
import com.agencelocation.repository.VehiculeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToVehiculeTypeConverter implements Converter<String, VehiculeType> {

    @Autowired
    private VehiculeTypeRepository vehiculeTypeRepository;

    @Override
    public VehiculeType convert(String source) {
        try {
            Long id = Long.valueOf(source); // Convertir l'ID en Long
            return vehiculeTypeRepository.findById(id).orElse(null); // Charger l'objet depuis la base de données
        } catch (NumberFormatException e) {
            return null; // Retourner null si la conversion échoue
        }
    }
}
