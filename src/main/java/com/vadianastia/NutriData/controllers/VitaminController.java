package com.vadianastia.NutriData.controllers;

import com.vadianastia.NutriData.entities.Vitamin;
import com.vadianastia.NutriData.services.VitaminsService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/vitamins")
public class VitaminController {
    private final VitaminsService vitaminsService;

    @Autowired
    public VitaminController(VitaminsService vitaminsService) {
        this.vitaminsService = vitaminsService;
    }

    @GetMapping
    public ResponseEntity<List<Vitamin>> getAllVitamins() {
        return vitaminsService.getAllVitamins();
    }


    @PostMapping
    public ResponseEntity<Vitamin> createVitamin(@RequestBody Vitamin vitamin) {
        return vitaminsService.createVitamin(vitamin);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAll() {
        return vitaminsService.deleteAll();

    }
}