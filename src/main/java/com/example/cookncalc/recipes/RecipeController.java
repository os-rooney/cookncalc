package com.example.cookncalc.recipes;

import com.example.cookncalc.recipeIngredient.RecipeIngredientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    public RecipeController(RecipeRepository recipeRepository, RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeRepository = recipeRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

@GetMapping("/api/test")
    public List<Object[]> test(){
       return this.recipeRepository.getAmountPerIngredientPerMarket();

    }

    @GetMapping("/api/{id}")
    public List<Object[]> test(@PathVariable Long id){
        return this.recipeRepository.getTotalAmountPerMarketForRecipeId(id);

    }

    @GetMapping("/api/test/{recipeId}")
    public List<TotalAmountRow> recipeTest(@PathVariable Long recipeId){

        return  new TotalAmountDTO(this.recipeRepository.getTotalAmountPerMarketForRecipeId(recipeId)).getResTable();

    }

}
