package com.example.cookncalc;

import com.example.cookncalc.ingredient.IngredientDTO;
import com.example.cookncalc.recipe.RecipeDTO;
import com.example.cookncalc.recipe.TotalPriceForRecipe;
import com.example.cookncalc.recipeIngredient.RecipeIngredientDTO;
import com.example.cookncalc.recipeIngredient.RecipeIngredientDTOII;
import com.example.cookncalc.recipe.RecipeDTO;
import com.example.cookncalc.recipe.RecipeRepository;
import com.example.cookncalc.recipe.TotalPriceForRecipe;
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

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/api")
    public List<RecipeDTO> home() {
        return homeService.showRecipes();
    }

    @GetMapping("/api/myrecipes")
    public List<RecipeDTO> getMyRecipes(@RequestParam long id, @RequestParam String username, @RequestParam boolean admin) {
        System.out.println(id);
        System.out.println(username);
        System.out.println(admin);
        return homeService.findRecipeByUser(id);
    }

    @GetMapping("/api/recipes/{id}")
    public RecipeIngredientDTOII detail(@PathVariable Long id) {
        return homeService.showDetailRecipe(id);
    }

    @PostMapping("/api/recipes/add")
    public void add(@RequestBody RecipeIngredientDTO dto) {
        if (homeService.checkForDuplicateIngredients(dto)
                && homeService.checkForAllowedIngredientNames(dto)
                && homeService.checkIfRecipeIsFilled(dto)) {
            homeService.addRecipe(dto);
        }
    }



    @DeleteMapping("/api/recipes/{id}/delete")
    public void deleteRecipe(@PathVariable Long id){
        homeService.deleteRecipe(id);
    }

    @PostMapping("/api/recipes/{id}/edit")
    public void change(@PathVariable Long id, @RequestBody RecipeIngredientDTO dto) {
        System.out.println(dto.getIngredients().get(0).getUnit());
        System.out.println(dto.getIngredients().get(0).getName());
        if (homeService.checkForDuplicateIngredients(dto)
                && homeService.checkForAllowedIngredientNames(dto)
                && homeService.checkIfRecipeIsFilled(dto)) {
            homeService.deleteRecipe(id);
            homeService.addRecipe(dto);
        }
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