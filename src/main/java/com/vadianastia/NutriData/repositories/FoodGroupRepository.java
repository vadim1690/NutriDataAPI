package com.vadianastia.NutriData.repositories;

import com.vadianastia.NutriData.entities.FoodGroup;
import com.vadianastia.NutriData.entities.Vitamin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;

import java.util.List;


public interface FoodGroupRepository extends JpaRepository<FoodGroup, String> {

    List<FoodGroup> findAllByNameIgnoreCase(String name);
}
