package com.example.cookncalc.DTO;

import com.example.cookncalc.ingredient.Ingredient;

import java.util.List;

public class DTO {

    private String name;
    private String unit;
    private double amount;
    private String title;
    private String description;
    private String preparation;

    public DTO(){
    }

    public DTO(String name, String unit, double amount, String title, String description, String preparation) {
        this.name = name;
        this.unit = unit;
        this.amount = amount;
        this.title = title;
        this.description = description;
        this.preparation = preparation;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }
}
