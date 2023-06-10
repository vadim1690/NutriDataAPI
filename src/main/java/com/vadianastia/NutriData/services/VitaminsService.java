package com.vadianastia.NutriData.services;


import com.vadianastia.NutriData.entities.Vitamin;
import com.vadianastia.NutriData.repositories.VitaminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VitaminsService {

    private final VitaminRepository vitaminRepository;

    @Autowired
    public VitaminsService(VitaminRepository vitaminRepository) {
        this.vitaminRepository = vitaminRepository;
    }

    public ResponseEntity<List<Vitamin>> getAllVitamins() {
        List<Vitamin> vitamins = vitaminRepository.findAll();
        return new ResponseEntity<>(vitamins, HttpStatus.OK);
    }

    public ResponseEntity<Vitamin> createVitamin(Vitamin vitamin) {
        Vitamin savedData = vitaminRepository.save(vitamin);
        return new ResponseEntity<>(savedData, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> deleteAll() {
        vitaminRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
