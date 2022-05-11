package com.example.cookncalc;

import com.example.cookncalc.DTO.DTO;
import com.example.cookncalc.ingredient.Ingredient;
import com.example.cookncalc.ingredient.IngredientRepository;
import com.example.cookncalc.recipeIngredient.RecipeIngredient;
import com.example.cookncalc.recipeIngredient.RecipeIngredientRespository;
import com.example.cookncalc.recipes.Recipe;
import com.example.cookncalc.recipes.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    private final RecipeRepository recipeRepository;
    private final RecipeIngredientRespository recipeIngredientRespository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public HomeController(RecipeRepository recipeRepository, RecipeIngredientRespository recipeIngredientRespository, IngredientRepository ingredientRepository){
        this.recipeRepository = recipeRepository;
        this.recipeIngredientRespository = recipeIngredientRespository;
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/api")
    public List<Recipe> home(){
        return recipeRepository.findAll();
    }

    @PostMapping("/addData")
    public void add(@RequestBody DTO dto){
        Recipe recipe = new Recipe();
        recipe.setTitle(dto.getTitle());
        recipe.setDescription(dto.getDescription());
        recipe.setPreparation(dto.getPreparation());
        Ingredient ingredient = new Ingredient();
        ingredient.setName(dto.getName());
        ingredient.setUnit(dto.getUnit());
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setAmount(dto.getAmount());
        recipeIngredient.setRecipe(recipe);
        recipeIngredient.setIngredient(ingredient);

        recipeRepository.save(recipe);
        ingredientRepository.save(ingredient);
        recipeIngredientRespository.save(recipeIngredient);

    }
}
