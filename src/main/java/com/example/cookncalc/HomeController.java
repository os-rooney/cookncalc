package com.example.cookncalc;

import com.example.cookncalc.ingredient.IngredientDTO;
import com.example.cookncalc.json.RecipeWithIngredientsDTO;
import com.example.cookncalc.recipes.RecipeDTO;
import com.example.cookncalc.services.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final RecipeService recipeService;

    @Autowired
    public HomeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @GetMapping("/api")
    public List<RecipeDTO> home(){
        return recipeService.showRecipes();
    }

    @GetMapping("/api/myrecipe")
    public List<RecipeDTO> getMyRecipes(@RequestParam long id, @RequestParam String username, @RequestParam boolean admin){
        return recipeService.findRecipeByUser(id);
    }

    @GetMapping("/api/recipe/{id}")
    public RecipeWithIngredientsDTO detail(@PathVariable Long id){
        return recipeService.showDetailRecipe(id);
    }

    @PostMapping("/api/addRecipe")
    public void add(@RequestBody RecipeWithIngredientsDTO dto){
        recipeService.addRecipe(dto);
    }

    @GetMapping("/api/ingredients")
    public List<IngredientDTO> fillDropdown() {
        return recipeService.ingredientsForDropdown();
    }

    @DeleteMapping("/api/deleteRecipe/{id}")
    public void deleteRecipe(@PathVariable Long id){
        recipeService.deleteRecipe(id);
    }

    @PostMapping("/api/recipe/{id}/edit")
    public void change(@PathVariable Long id, @RequestBody RecipeWithIngredientsDTO dto){
        recipeService.deleteRecipe(id);
        recipeService.addRecipe(dto);
    }
}