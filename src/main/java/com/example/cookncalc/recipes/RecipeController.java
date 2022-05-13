package com.example.cookncalc.recipes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipeController {
    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

@GetMapping("/api/test")
    public List<Object[]> test(){
       return this.recipeRepository.getAmountPerIngredientPerMarket();

    }

    @GetMapping("/api/{id}")
    public List<Object[]> test(@PathVariable Long id){
        return this.recipeRepository.getTotalAmountPerMarketForRecipeId(id);

    }

}
