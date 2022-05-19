package com.example.cookncalc.recipeIngredient;

import com.example.cookncalc.ingredient.IngredientDTO;
import com.example.cookncalc.user.User;
import com.example.cookncalc.user.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class RecipeIngredientDTO {

    private String title;
    private String description;
    private String preparation;

    private Double amount;
    private  List<IngredientDTO> ingredients = new ArrayList<>();

    private User user;

    public RecipeIngredientDTO(){
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
