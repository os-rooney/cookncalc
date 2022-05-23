package com.example.cookncalc.recipe;

import java.math.BigInteger;

public class IngredientPriceForRecipe {
    BigInteger recipeId;
    String recipeTitle;
    String supermarket;

    String ingredient;
    double vpeNeed;
    double totalPrice;

    public IngredientPriceForRecipe(Object[] objects){
        for(int i=0; i<objects.length; i++){
            switch (i){
                case 0:
                    if(objects[i] instanceof BigInteger){
                        recipeId = (BigInteger) objects[i];
                    }
                    break;
                case 1:
                    if(objects[i] instanceof String){
                        recipeTitle = (String) objects[i];
                    }
                    break;
                case 2:
                    if(objects[i] instanceof String){
                        supermarket = (String) objects[i];
                    }
                    break;
                case 3:
                    if(objects[i] instanceof String){
                        ingredient = (String) objects[i];
                    }
                case 4:
                    if(objects[i] instanceof Double){
                        vpeNeed = (double) objects[i];
                    }
                    break;
                case 5:
                    if(objects[i] instanceof Double){
                        totalPrice = (double) objects[i];
                    }
                    break;
                default:
                    System.out.println("Error!!!!!!!!!!!");
            }
        }

    }

    public BigInteger getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(BigInteger recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getSupermarket() {
        return supermarket;
    }

    public void setSupermarket(String supermarket) {
        this.supermarket = supermarket;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public double getVpeNeed() {
        return vpeNeed;
    }

    public void setVpeNeed(double vpeNeed) {
        this.vpeNeed = vpeNeed;
    }
}
