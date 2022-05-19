package com.example.cookncalc.recipeIngredient;

public class RecipeIngredientDTO {

    private double amount;

    public RecipeIngredientDTO(){
    }

    public RecipeIngredientDTO(Double amount){
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}