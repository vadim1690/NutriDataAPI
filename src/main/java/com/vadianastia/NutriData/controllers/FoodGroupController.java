package com.vadianastia.NutriData.controllers;

import com.vadianastia.NutriData.entities.FoodGroup;
import com.vadianastia.NutriData.services.FoodGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foodGroups")
public class FoodGroupController {
    private final FoodGroupService foodGroupService;

    @Autowired
    public FoodGroupController(FoodGroupService foodGroupService) {
        this.foodGroupService = foodGroupService;
    }

    @GetMapping
    public ResponseEntity<List<FoodGroup>> getAllFoodGroups() {
        return foodGroupService.getAllFoodGroups();

    }



    @PostMapping
    public ResponseEntity<FoodGroup> createFoodGroup(@RequestBody FoodGroup foodGroup) {
        return foodGroupService.createFoodGroup(foodGroup);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAll() {
        return foodGroupService.deleteAll();
    }
}