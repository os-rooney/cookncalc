package com.example.cookncalc.recipeIngredient;

import com.example.cookncalc.ingredient.Ingredient;
import com.example.cookncalc.recipes.Recipe;

import javax.persistence.*;

@Entity
public class RecipeIngredient {

    @EmbeddedId
    private RecipeIngredientKey id = new RecipeIngredientKey();

    @ManyToOne
    @MapsId("recipeId")
    @JoinColumn(name ="recipe_Id")
    private Recipe recipe;

    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private double amount;

    public RecipeIngredient(){}


    public RecipeIngredientKey getId() {
        return id;
    }

    public void setId(RecipeIngredientKey id) {
        this.id = id;
    }

/*    public Recipe getRecipe() {
        return recipe;
    }*/

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

/*    public Ingredient getIngredient() {
        return ingredient;
    }*/

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RecipeIngredient other = (RecipeIngredient) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
