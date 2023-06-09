package com.vadianastia.NutriData.controllers;

import com.vadianastia.NutriData.entities.FoodGroup;
import com.vadianastia.NutriData.entities.ProductData;
import com.vadianastia.NutriData.entities.Vitamin;
import com.vadianastia.NutriData.repositories.FoodGroupRepository;
import com.vadianastia.NutriData.repositories.ProductRepository;
import com.vadianastia.NutriData.repositories.VitaminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final FoodGroupRepository foodGroupRepository;
    private final VitaminRepository vitaminRepository;

    @Autowired

    public ProductController(ProductRepository productRepository, FoodGroupRepository foodGroupRepository, VitaminRepository vitaminRepository) {
        this.productRepository = productRepository;
        this.foodGroupRepository = foodGroupRepository;
        this.vitaminRepository = vitaminRepository;
    }

    @GetMapping
    public ResponseEntity<List<ProductData>> getAllNutriData() {
        List<ProductData> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<ProductData>> getNutriDataByProductName(@PathVariable String name) {
        List<ProductData> products = productRepository.findAllByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<ProductData>> createProducts(@RequestBody List<ProductData> productsData) {
        List<ProductData> result = new ArrayList<>();
        for (ProductData productData : productsData)
            result.add(createProduct(productData));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    public ProductData createProduct(ProductData productData) {
        FoodGroup foodGroup = productData.getFoodGroup();
        if (foodGroup != null) {
            List<FoodGroup> foodGroupsForProduct = foodGroupRepository.findAllByName(foodGroup.getName());

            if (foodGroupsForProduct != null && foodGroupsForProduct.size() > 0)
                foodGroup = foodGroupsForProduct.get(0);
            else
                foodGroup = foodGroupRepository.save(foodGroup);

            productData.setFoodGroup(foodGroup);

        }


        List<Vitamin> vitaminsFromProduct = productData.getVitamins();
        if (vitaminsFromProduct != null && !vitaminsFromProduct.isEmpty()) {
            List<Vitamin> vitaminsFromDB = new ArrayList<>();
            for (Vitamin vitaminFromProduct : vitaminsFromProduct) {
                List<Vitamin> vitaminsSearchResult = vitaminRepository.findAllByName(vitaminFromProduct.getName());
                if (vitaminsSearchResult != null && !vitaminsSearchResult.isEmpty())
                    vitaminsFromDB.addAll(vitaminsSearchResult);
                else
                    vitaminsFromDB.add(vitaminRepository.save(vitaminFromProduct));
            }
            productData.setVitamins(vitaminsFromProduct);
        }


        ProductData savedData = productRepository.save(productData);
        return savedData;
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
        productRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}