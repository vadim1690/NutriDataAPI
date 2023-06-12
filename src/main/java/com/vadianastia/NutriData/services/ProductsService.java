package com.vadianastia.NutriData.services;

import com.vadianastia.NutriData.entities.FoodGroup;
import com.vadianastia.NutriData.entities.ProductData;
import com.vadianastia.NutriData.entities.Vitamin;
import com.vadianastia.NutriData.repositories.FoodGroupRepository;
import com.vadianastia.NutriData.repositories.ProductRepository;
import com.vadianastia.NutriData.repositories.VitaminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductsService {
    private final ProductRepository productRepository;
    private final FoodGroupRepository foodGroupRepository;
    private final VitaminRepository vitaminRepository;

    @Autowired
    public ProductsService(ProductRepository productRepository, FoodGroupRepository foodGroupRepository, VitaminRepository vitaminRepository) {
        this.productRepository = productRepository;
        this.foodGroupRepository = foodGroupRepository;
        this.vitaminRepository = vitaminRepository;
    }

    public ResponseEntity<List<ProductData>> getAllNutriData() {
        List<ProductData> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductData>> getNutriDataByProductName(String name) {
        List<ProductData> products = productRepository.findAllByNameIgnoreCase(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductData>> createProducts(List<ProductData> productsData) {
        List<ProductData> result = new ArrayList<>();
        for (ProductData productData : productsData)
            result.add(createProduct(productData));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    public ProductData createProduct(ProductData productData) {

        FoodGroup foodGroup = productData.getFoodGroup();
        if (foodGroup != null) {
            List<FoodGroup> foodGroupsForProduct = foodGroupRepository.findAllByNameIgnoreCase(foodGroup.getName());

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

    public ResponseEntity<Object> deleteAll() {
        productRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<ProductData>> getNutriDataByFoodGroup(String name) {
        Set<ProductData> resultList = new HashSet<>();
        List<FoodGroup> foodGroups = foodGroupRepository.findAllByNameIgnoreCase(name);
        if (foodGroups != null && !foodGroups.isEmpty()) {
            for (FoodGroup foodGroup : foodGroups) {
                List<ProductData> productsData = productRepository.findAllByFoodGroup(foodGroup);
                if (productsData != null && !productsData.isEmpty())
                    resultList.addAll(productsData);
            }
        }
        return new ResponseEntity<>(resultList.stream().toList(), HttpStatus.OK);
    }

    public ResponseEntity<List<ProductData>> getNutriDataByVitamin(String name) {
        List<ProductData> res = productRepository.findAllByVitamins_NameIgnoreCase(name);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductData>> getAllNaturalNutriData() {
        return new ResponseEntity<>(productRepository.findAllByIsNatural(true), HttpStatus.OK);
    }

    public ResponseEntity<List<ProductData>> getAllNotNaturalNutriData() {
        return new ResponseEntity<>(productRepository.findAllByIsNatural(false), HttpStatus.OK);
    }

    public ResponseEntity<Double> getCaloriesForProduct(String name) {
        return new ResponseEntity<>(productRepository.findCaloriesByNameIgnoreCase(name), HttpStatus.OK);
    }

    public ResponseEntity<Double> getSugarForProduct(String name) {
        return new ResponseEntity<>(productRepository.findSugarByNameIgnoreCase(name), HttpStatus.OK);
    }
}
