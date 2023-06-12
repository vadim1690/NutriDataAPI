package com.vadianastia.NutriData.controllers;

import com.vadianastia.NutriData.entities.ProductData;
import com.vadianastia.NutriData.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductsService productsService;

    @Autowired

    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public ResponseEntity<List<ProductData>> getAllNutriData() {
        return productsService.getAllNutriData();
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<ProductData>> getNutriDataByProductName(@PathVariable String name) {
        return productsService.getNutriDataByProductName(name);
    }

    @GetMapping("/ProductsByFoodGroup/{name}")
    public ResponseEntity<List<ProductData>> getNutriDataByFoodGroup(@PathVariable String name) {
        return productsService.getNutriDataByFoodGroup(name);
    }

    @GetMapping("/ProductsByVitamin/{name}")
    public ResponseEntity<List<ProductData>> getAllNutriDataByVitamin(@PathVariable String name) {
        return productsService.getNutriDataByVitamin(name);
    }

    @GetMapping("/isNatural")
    public ResponseEntity<List<ProductData>> getAllNaturalNutriData() {
        return productsService.getAllNaturalNutriData();
    }

    @GetMapping("/notNatural")
    public ResponseEntity<List<ProductData>> getAllNotNaturalNutriData() {
        return productsService.getAllNotNaturalNutriData();
    }

    @GetMapping("/Calories/{name}")
    public ResponseEntity<Double> getCaloriesForProduct(@PathVariable String name) {
        return productsService.getCaloriesForProduct(name);
    }

    @GetMapping("/Sugar/{name}")
    public ResponseEntity<Double> getSugarForProduct(@PathVariable String name) {
        return productsService.getSugarForProduct(name);
    }

    @PostMapping
    public ResponseEntity<List<ProductData>> createProducts(@RequestBody List<ProductData> productsData) {
        return productsService.createProducts(productsData);

    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAll() {
        return productsService.deleteAll();
    }
}