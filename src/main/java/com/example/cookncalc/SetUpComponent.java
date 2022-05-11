package com.example.cookncalc;

import com.example.cookncalc.ingredient.Ingredient;
import com.example.cookncalc.ingredient.IngredientRepository;
import com.example.cookncalc.recipeIngredient.RecipeIngredient;
import com.example.cookncalc.recipeIngredient.RecipeIngredientRespository;
import com.example.cookncalc.recipes.Recipe;
import com.example.cookncalc.recipes.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class SetUpComponent {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRespository recipeIngredientRespository;

    @Autowired
    public SetUpComponent(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, RecipeIngredientRespository recipeIngredientRespository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRespository = recipeIngredientRespository;
    }

    @EventListener
    @Transactional
    public void handleApplicationReady(ApplicationReadyEvent event) {
        Recipe recipe = new Recipe("Kuchen", "Bester Kuchen");
        Ingredient eins = new Ingredient("Butter", "stück");
        Ingredient zwei = new Ingredient("Marmelade", "Glas");
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setRecipe(recipe);
        recipeIngredient.setIngredient(eins);
        RecipeIngredient recipeIngredient1 = new RecipeIngredient();
        recipeIngredient1.setRecipe(recipe);
        recipeIngredient1.setIngredient(zwei);
        recipeIngredient1.setAmount(2);
        recipeIngredient.setAmount(3);

        if (recipeRepository.count() == 0) {
            recipeRepository.save(recipe);
            ingredientRepository.save(eins);
            ingredientRepository.save(zwei);
            recipeIngredientRespository.save(recipeIngredient);
            recipeIngredientRespository.save(recipeIngredient1);
        }
    }
}