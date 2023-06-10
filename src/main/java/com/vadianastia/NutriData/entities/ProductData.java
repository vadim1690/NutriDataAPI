package com.vadianastia.NutriData.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class ProductData {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    private String name;

    private String description;

    @ElementCollection
    private List<String> ingredients;

    private Double calories;
    @ManyToOne(fetch = FetchType.EAGER)
    private FoodGroup foodGroup;
    private Double fat;
    private Double sodium;
    private Double carbohydrates;
    private String imageUrl;
    private Boolean isNatural;
    private Double fiber;
    private Double sugar;
    private Double protein;
    private Double potassium;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vitamin> vitamins;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public FoodGroup getFoodGroup() {
        return foodGroup;
    }

    public void setFoodGroup(FoodGroup foodGroup) {
        this.foodGroup = foodGroup;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getSodium() {
        return sodium;
    }

    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getNatural() {
        return isNatural;
    }

    public void setNatural(Boolean natural) {
        isNatural = natural;
    }

    public Double getFiber() {
        return fiber;
    }

    public void setFiber(Double fiber) {
        this.fiber = fiber;
    }

    public Double getSugar() {
        return sugar;
    }

    public void setSugar(Double sugar) {
        this.sugar = sugar;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getPotassium() {
        return potassium;
    }

    public void setPotassium(Double potassium) {
        this.potassium = potassium;
    }

    public List<Vitamin> getVitamins() {
        return vitamins;
    }

    public void setVitamins(List<Vitamin> vitamins) {
        this.vitamins = vitamins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductData that = (ProductData) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(ingredients, that.ingredients) && Objects.equals(calories, that.calories) && Objects.equals(foodGroup, that.foodGroup) && Objects.equals(fat, that.fat) && Objects.equals(sodium, that.sodium) && Objects.equals(carbohydrates, that.carbohydrates) && Objects.equals(imageUrl, that.imageUrl) && Objects.equals(isNatural, that.isNatural) && Objects.equals(fiber, that.fiber) && Objects.equals(sugar, that.sugar) && Objects.equals(protein, that.protein) && Objects.equals(potassium, that.potassium) && Objects.equals(vitamins, that.vitamins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, ingredients, calories, foodGroup, fat, sodium, carbohydrates, imageUrl, isNatural, fiber, sugar, protein, potassium, vitamins);
    }
}
