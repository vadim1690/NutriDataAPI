package com.vadianastia.NutriData.repositories;

import com.vadianastia.NutriData.entities.FoodGroup;
import com.vadianastia.NutriData.entities.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductRepository extends JpaRepository<ProductData, String> {
    List<ProductData> findAllByNameIgnoreCase(String name);
    List<ProductData> findAllByFoodGroup(FoodGroup foodGroup);
    List<ProductData> findAllByVitamins_NameIgnoreCase(String vitaminName);

    List<ProductData> findAllByIsNatural(boolean isNatural);
    @Query("SELECT p.calories FROM ProductData p WHERE LOWER(p.name) = LOWER(:name)")
    Double findCaloriesByNameIgnoreCase(String name);

    @Query("SELECT p.sugar FROM ProductData p WHERE LOWER(p.name) = LOWER(:name)")
    Double findSugarByNameIgnoreCase(String name);
}
