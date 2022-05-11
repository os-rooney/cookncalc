package com.example.cookncalc;

import com.example.cookncalc.json.JsonDTO;
import com.example.cookncalc.ingredient.IngredientRepository;
import com.example.cookncalc.recipeIngredient.RecipeIngredientRespository;
import com.example.cookncalc.recipes.Recipe;
import com.example.cookncalc.recipes.RecipeRepository;
import com.example.cookncalc.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    private final RecipeRepository recipeRepository;
    private final RecipeIngredientRespository recipeIngredientRespository;
    private final IngredientRepository ingredientRepository;
    private final RecipeService recipeService;

    @Autowired
    public HomeController(RecipeRepository recipeRepository,
                          RecipeIngredientRespository recipeIngredientRespository,
                          IngredientRepository ingredientRepository,
                          RecipeService recipeService){
        this.recipeRepository = recipeRepository;
        this.recipeIngredientRespository = recipeIngredientRespository;
        this.ingredientRepository = ingredientRepository;
        this.recipeService = recipeService;
    }

    @GetMapping("/api")
    public List<Recipe> home(){
        return recipeRepository.findAll();
    }

    @PostMapping("/api/addRecipe")
    public void add(@RequestBody JsonDTO dto){
        recipeService.addRecipe(dto);
    }
}
