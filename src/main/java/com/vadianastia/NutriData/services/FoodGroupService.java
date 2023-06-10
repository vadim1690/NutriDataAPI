package com.vadianastia.NutriData.services;

import com.vadianastia.NutriData.entities.FoodGroup;
import com.vadianastia.NutriData.repositories.FoodGroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodGroupService {

    private final FoodGroupRepository foodGroupRepository;


    @Autowired
    public FoodGroupService(FoodGroupRepository foodGroupRepository) {
        this.foodGroupRepository = foodGroupRepository;
    }

    public ResponseEntity<List<FoodGroup>> getAllFoodGroups() {
        List<FoodGroup> foodGroups = foodGroupRepository.findAll();
        return new ResponseEntity<>(foodGroups, HttpStatus.OK);
    }

    public ResponseEntity<FoodGroup> createFoodGroup(FoodGroup foodGroup) {
        FoodGroup savedData = foodGroupRepository.save(foodGroup);
        return new ResponseEntity<>(savedData, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> deleteAll() {
        foodGroupRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
