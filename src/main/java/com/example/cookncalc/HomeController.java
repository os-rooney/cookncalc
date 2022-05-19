package com.example.cookncalc;

import com.example.cookncalc.ingredient.IngredientDTO;
import com.example.cookncalc.recipeIngredient.RecipeIngredientDTO;
import com.example.cookncalc.recipes.RecipeDTO;
import com.example.cookncalc.recipes.RecipeRepository;
import com.example.cookncalc.recipes.TotalPriceForRecipe;
import com.example.cookncalc.supermarketIngredient.SupermarketIngredient;
import com.example.cookncalc.supermarketIngredient.SupermarketIngredientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final HomeService homeService;

    private final RecipeRepository recipeRepository;

    private final SupermarketIngredientRepository supermarketIngredientRepository;

    @Autowired
    public HomeController(HomeService homeService, RecipeRepository recipeRepository, SupermarketIngredientRepository supermarketIngredientRepository){
        this.homeService = homeService;
        this.recipeRepository = recipeRepository;
        this.supermarketIngredientRepository = supermarketIngredientRepository;
    }

    @GetMapping("/api")
    public List<RecipeDTO> home(){
        return homeService.showRecipes();
    }

    @GetMapping("/api/myrecipes")
    public List<RecipeDTO> getMyRecipes(@RequestParam long id, @RequestParam String username, @RequestParam boolean admin){
        return homeService.findRecipeByUser(id);
    }

    @GetMapping("/api/recipes/{id}")
    public RecipeIngredientDTO detail(@PathVariable Long id){
        return homeService.showDetailRecipe(id);
    }

    @PostMapping("/api/recipes/add")
    public void add(@RequestBody RecipeIngredientDTO dto){
        homeService.addRecipe(dto);
    }

    @DeleteMapping("/api/recipes/{id}/delete")
    public void deleteRecipe(@PathVariable Long id){
        homeService.deleteRecipe(id);
    }

    @PostMapping("/api/recipe/{id}/edit")
    public void change(@PathVariable Long id, @RequestBody RecipeIngredientDTO dto){
        homeService.deleteRecipe(id);
        homeService.addRecipe(dto);
    }

    @GetMapping("/api/recipes/{id}/calculation")
    public List<TotalPriceForRecipe> recipeTest(@PathVariable Long id){
        return  homeService.priceCalculation(id);
    }

    @GetMapping("/api/prices/{id}")
    public List<SupermarketIngredient> getIngredientPrice(@PathVariable("id") Long id){
        return homeService.getIngredientPrices(id);
    }

    @GetMapping("/api/ingredients")
    public List<IngredientDTO> fillDropdown() {
        return homeService.ingredientsForDropdown();
    }
}