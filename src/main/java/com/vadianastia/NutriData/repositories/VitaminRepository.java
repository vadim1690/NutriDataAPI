package com.vadianastia.NutriData.repositories;

import com.vadianastia.NutriData.entities.ProductData;
import com.vadianastia.NutriData.entities.Vitamin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VitaminRepository extends JpaRepository<Vitamin, String> {

    List<Vitamin> findAllByName(String name);
}
