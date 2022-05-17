package com.example.cookncalc.recipes;

import com.example.cookncalc.recipeIngredient.RecipeIngredientRepository;
import com.example.cookncalc.services.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipeService recipeService;

    public RecipeController(RecipeRepository recipeRepository, RecipeIngredientRepository recipeIngredientRepository, RecipeService recipeService) {
        this.recipeRepository = recipeRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recipeService = recipeService;
    }

    @GetMapping("/api/test/{recipeId}")
    public List<TotalPriceForRecipe> recipeTest(@PathVariable Long recipeId){
        return  recipeService.priceCalculation(this.recipeRepository.getTotalAmountPerMarketForRecipeId(recipeId));
    }
}
