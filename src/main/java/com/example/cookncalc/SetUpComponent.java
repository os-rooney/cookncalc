package com.example.cookncalc;

import com.example.cookncalc.ingredient.Ingredient;
import com.example.cookncalc.ingredient.IngredientRepository;
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

    @Autowired
    public SetUpComponent(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @EventListener
    @Transactional
    public void handleApplicationReady(ApplicationReadyEvent event) {
        List<Ingredient> liste = new ArrayList<>();
        liste.add(new Ingredient("Butter", 2.0,"stück"));
        liste.add(new Ingredient("Marmelade", 1.0, "Glas"));
        ingredientRepository.save(liste.get(0));
        ingredientRepository.save(liste.get(1));

        if (recipeRepository.count() == 0) {
            recipeRepository.save(new Recipe("Erstes Rezept", liste, "BlaBlaBla"));
           // recipeRepository.save(new Recipe("Zweites Rezept", liste,"BlubBlubBlub"));

        }
    }
}