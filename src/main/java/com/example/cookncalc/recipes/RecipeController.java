package com.example.cookncalc.recipes;

import com.example.cookncalc.recipeIngredient.RecipeIngredientRespository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final RecipeIngredientRespository recipeIngredientRespository;

    public RecipeController(RecipeRepository recipeRepository, RecipeIngredientRespository recipeIngredientRespository) {
        this.recipeRepository = recipeRepository;
        this.recipeIngredientRespository = recipeIngredientRespository;
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
