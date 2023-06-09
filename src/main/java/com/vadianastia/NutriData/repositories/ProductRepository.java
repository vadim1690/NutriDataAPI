package com.vadianastia.NutriData.repositories;

import com.vadianastia.NutriData.entities.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<ProductData, String> {
    List<ProductData> findAllByName(String name);
}
