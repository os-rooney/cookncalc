package com.example.cookncalc.ingredient;

import com.example.cookncalc.recipes.Recipe;
import com.sun.xml.bind.v2.TODO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Double amount;

    //TODO: Enum erstellen
    private String unit;

    @ManyToOne
    private Recipe recipe;

    public Ingredient(){

    }

    public Ingredient(String name, Double amount, String unit){
        this.name = name;
        this.amount = amount;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
