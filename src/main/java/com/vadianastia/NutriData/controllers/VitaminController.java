package com.vadianastia.NutriData.controllers;

import com.vadianastia.NutriData.entities.Vitamin;
import com.vadianastia.NutriData.repositories.VitaminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vitamins")
public class VitaminController {
    private final VitaminRepository vitaminRepository;

    @Autowired
    public VitaminController(VitaminRepository vitaminRepository) {
        this.vitaminRepository = vitaminRepository;
    }

    @GetMapping
    public ResponseEntity<List<Vitamin>> getAllVitamins() {
        List<Vitamin> vitamins = vitaminRepository.findAll();
        return new ResponseEntity<>(vitamins, HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
//        Optional<Task> optionalTask = taskRepository.findById(id);
//        return optionalTask.map(task -> new ResponseEntity<>(task, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    @PostMapping
    public ResponseEntity<Vitamin> createTask(@RequestBody Vitamin vitamin) {
        Vitamin savedData = vitaminRepository.save(vitamin);
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
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> deleteTask(@PathVariable Long id) {
//        Optional<Task> optionalTask = taskRepository.findById(id);
//        return optionalTask.map(task -> {
//            taskRepository.delete(task);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAll() {
        vitaminRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}