package com.example.cookncalc.ingredient;

public class IngredientDTO {

    private Long id;
    private String name;

    //TODO: Enum erstellen
    private String unit;

    private Double amount;

    public IngredientDTO(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }

    public IngredientDTO(){

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
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
