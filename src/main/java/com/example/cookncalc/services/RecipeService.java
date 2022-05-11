package com.example.cookncalc.services;

import com.example.cookncalc.json.JsonDTO;
import com.example.cookncalc.ingredient.Ingredient;
import com.example.cookncalc.ingredient.IngredientDTO;
import com.example.cookncalc.ingredient.IngredientRepository;
import com.example.cookncalc.recipeIngredient.RecipeIngredient;
import com.example.cookncalc.recipeIngredient.RecipeIngredientRespository;
import com.example.cookncalc.recipes.Recipe;
import com.example.cookncalc.recipes.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRespository recipeIngredientRespository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository,
                         IngredientRepository ingredientRepository,
                         RecipeIngredientRespository recipeIngredientRespository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRespository = recipeIngredientRespository;
    }

    public void addRecipe(JsonDTO dto){
        Recipe recipe = new Recipe();
        recipe.setTitle(dto.getTitle());
        recipe.setDescription(dto.getDescription());
        recipe.setPreparation(dto.getPreparation());
        recipeRepository.save(recipe);
        for(IngredientDTO ingredientDTO : dto.getIngredients()){
            Double amount = ingredientDTO.getAmount();
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setRecipe(recipe);
            Ingredient ingredient = new Ingredient(ingredientDTO.getName(), ingredientDTO.getUnit());
            recipeIngredient.setIngredient(ingredient);
            recipeIngredient.setAmount(amount);
            ingredientRepository.save(ingredient);
            recipeIngredientRespository.save(recipeIngredient);
        }
    }

}