package com.example.cookncalc.services;

import com.example.cookncalc.json.RecipeWithIngredientsDTO;
import com.example.cookncalc.ingredient.Ingredient;
import com.example.cookncalc.ingredient.IngredientDTO;
import com.example.cookncalc.ingredient.IngredientRepository;
import com.example.cookncalc.recipeIngredient.RecipeIngredient;
import com.example.cookncalc.recipeIngredient.RecipeIngredientRespository;
import com.example.cookncalc.recipes.Recipe;
import com.example.cookncalc.recipes.RecipeDTO;
import com.example.cookncalc.recipes.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


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


    public List<RecipeDTO> showRecipes(){
        List<RecipeDTO> recipeDTO = new LinkedList<RecipeDTO>();
        List<Recipe> recipes = recipeRepository.findAll();
        for(Recipe recipe : recipes){
            RecipeDTO recipeDTO1 =  new RecipeDTO();
            recipeDTO1.setId(recipe.getId());
            recipeDTO1.setTitle(recipe.getTitle());
            recipeDTO1.setDescription(recipe.getDescription());
            recipeDTO.add(recipeDTO1);
        }
        return recipeDTO;
    }
    public void addRecipe(RecipeWithIngredientsDTO dto){
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