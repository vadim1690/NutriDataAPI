package com.vadianastia.NutriData.repositories;

import com.vadianastia.NutriData.entities.FoodGroup;
import com.vadianastia.NutriData.entities.ProductData;
import com.vadianastia.NutriData.entities.Vitamin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<ProductData, String> {
    List<ProductData> findAllByNameIgnoreCase(String name);
    List<ProductData> findAllByFoodGroup(FoodGroup foodGroup);
    List<ProductData> findAllByVitamins_NameIgnoreCase(String vitaminName);

    List<ProductData> findAllByIsNatural(boolean isNatural);
}
