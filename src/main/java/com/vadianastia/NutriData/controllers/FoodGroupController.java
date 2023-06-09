package com.vadianastia.NutriData.controllers;

import com.vadianastia.NutriData.entities.FoodGroup;
import com.vadianastia.NutriData.repositories.FoodGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/foodGroups")
public class FoodGroupController {
    private final FoodGroupRepository foodGroupRepository;

    @Autowired
    public FoodGroupController(FoodGroupRepository foodGroupRepository) {
        this.foodGroupRepository = foodGroupRepository;
    }

    @GetMapping
    public ResponseEntity<List<FoodGroup>> getAllFoodGroups() {
        List<FoodGroup> foodGroups = foodGroupRepository.findAll();
        return new ResponseEntity<>(foodGroups, HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
//        Optional<Task> optionalTask = taskRepository.findById(id);
//        return optionalTask.map(task -> new ResponseEntity<>(task, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    @PostMapping
    public ResponseEntity<FoodGroup> createFoodGroup(@RequestBody FoodGroup foodGroup) {
        FoodGroup savedData = foodGroupRepository.save(foodGroup);
        return new ResponseEntity<>(savedData, HttpStatus.CREATED);
    }

    //    @PutMapping("/{id}")
//    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
//        Optional<Task> optionalTask = taskRepository.findById(id);
//        return optionalTask.map(task -> {
//            task.setTitle(taskDetails.getTitle());
//            task.setDescription(taskDetails.getDescription());
//            task.setCompleted(taskDetails.isCompleted());
//            Task updatedTask = taskRepository.save(task);
//            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
//        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
    @DeleteMapping
    public ResponseEntity<Object> deleteAll() {
        foodGroupRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}