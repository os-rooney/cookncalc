package com.example.cookncalc.ingredient;

import com.example.cookncalc.recipeIngredient.RecipeIngredient;
import com.example.cookncalc.supermarketIngredient.SupermarketIngredient;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

   private double packageSize;

    @OneToMany(mappedBy = "ingredient")
    private Set<RecipeIngredient> recipeIngredient = new HashSet<>();

    @OneToMany(mappedBy = "ingredient")
    private Set<SupermarketIngredient> supermarketIngredients = new HashSet<>();

    private String unit;

    public Ingredient(){}

    public Ingredient(String name, String unit, double packageSize){
        this.name = name;
        this.unit = unit;
        this.packageSize = packageSize;
    }

    public Ingredient(String name, String unit){
        this.name = name;
        this.unit = unit;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Set<RecipeIngredient> getRecipeIngredient() {
        return recipeIngredient;
    }

    public void setRecipeIngredient(Set<RecipeIngredient> recipeIngredient) {
        this.recipeIngredient = recipeIngredient;
    }

    public Set<SupermarketIngredient> getSupermarketIngredients() {
        return supermarketIngredients;
    }

    public void setSupermarketIngredients(Set<SupermarketIngredient> supermarketIngredients) {
        this.supermarketIngredients = supermarketIngredients;
    }

    public double getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(double packageSize) {
        this.packageSize = packageSize;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ingredient other = (Ingredient) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
