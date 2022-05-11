package com.example.cookncalc.ingredient;

public class IngredientDTO {

    private String name;

    //TODO: Enum erstellen
    private String unit;

    private Double amount;

    public IngredientDTO(String name, String unit) {
        this.name = name;
        this.unit = unit;
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

    public Double getAmount() {
        return amount;
    }
}
