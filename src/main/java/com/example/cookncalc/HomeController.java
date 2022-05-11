package com.example.cookncalc;

import com.example.cookncalc.recipeIngredient.RecipeIngredient;
import com.example.cookncalc.recipeIngredient.RecipeIngredientRespository;
import com.example.cookncalc.recipes.Recipe;
import com.example.cookncalc.recipes.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    private final RecipeRepository recipeRepository;
    private final RecipeIngredientRespository recipeIngredientRespository;

    @Autowired
    public HomeController(RecipeRepository recipeRepository, RecipeIngredientRespository recipeIngredientRespository){
        this.recipeRepository = recipeRepository;
        this.recipeIngredientRespository = recipeIngredientRespository;
    }

    @GetMapping("/api")
    public List<Recipe> home(){
        return recipeRepository.findAll();
    }
}
