package com.example.cookncalc.json;

import com.example.cookncalc.ingredient.IngredientDTO;

import java.util.List;

public class RecipeWithIngredientsDTO {

    private String title;
    private String description;
    private String preparation;

    private Double amount;
    private  List<IngredientDTO> ingredients;

    public RecipeWithIngredientsDTO(){
    }

    public RecipeWithIngredientsDTO(String title, String description, String preparation, List<IngredientDTO> ingredients, Double amount) {
        this.title = title;
        this.description = description;
        this.preparation = preparation;
        this.ingredients = ingredients;
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

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
