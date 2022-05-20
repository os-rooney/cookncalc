package com.example.cookncalc.recipeIngredient;

import com.example.cookncalc.ingredient.IngredientDTO;
import com.example.cookncalc.user.User;
import com.example.cookncalc.user.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class RecipeIngredientDTOII {
    private String title;
    private String description;
    private String preparation;

    private Double amount;
    private List<IngredientDTO> ingredients = new ArrayList<>();

    private UserDTO userDTO;

    public RecipeIngredientDTOII() {
    }

    public RecipeIngredientDTOII(String title, String description, String preparation, Double amount, List<IngredientDTO> ingredients, UserDTO userDTO) {
        this.title = title;
        this.description = description;
        this.preparation = preparation;
        this.amount = amount;
        this.ingredients = ingredients;
        this.userDTO = userDTO;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public UserDTO getUser() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
