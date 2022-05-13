package com.example.cookncalc;

import com.example.cookncalc.json.RecipeWithIngredientsDTO;
import com.example.cookncalc.recipes.Recipe;
import com.example.cookncalc.recipes.RecipeDTO;
import com.example.cookncalc.recipes.RecipeRepository;
import com.example.cookncalc.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
public class HomeController {

    private final RecipeRepository recipeRepository;
    private final RecipeService recipeService;

    @Autowired
    public HomeController(RecipeRepository recipeRepository,
                          RecipeService recipeService){
        this.recipeRepository = recipeRepository;
        this.recipeService = recipeService;
    }

    @GetMapping("/api")
    public List<RecipeDTO> home(){
        return recipeService.showRecipes();
    }

    @GetMapping("/api/recipe/{id}")
    public RecipeWithIngredientsDTO detail(@PathVariable Long id){
        return recipeService.showDetailRecipe(id);
    }

    @PostMapping("/api/addRecipe")
    public void add(@RequestBody RecipeWithIngredientsDTO dto){
        recipeService.addRecipe(dto);
    }

    @DeleteMapping("/api/deleteRecipe/{id}")
    public List<RecipeDTO> deleteRecipe(@PathVariable("id") Long id){
        Optional<Recipe> recipeToDeleteOptional = recipeRepository.findById(id);
        if(!recipeToDeleteOptional.isPresent()){
            return null;
        }
        Recipe recipeToDelete = recipeToDeleteOptional.get();
        recipeRepository.delete(recipeToDelete);
        return recipeService.showRecipes();
    }
}