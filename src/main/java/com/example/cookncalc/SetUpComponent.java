package com.example.cookncalc;

import com.example.cookncalc.ingredient.Ingredient;
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


    @Autowired
    public SetUpComponent(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @EventListener
    @Transactional
    public void handleApplicationReady(ApplicationReadyEvent event) {
        List<Ingredient> liste = new ArrayList<>();
        liste.add(new Ingredient());
        liste.add(new Ingredient());

        if (recipeRepository.count() == 0) {
            recipeRepository.save(new Recipe("Erstes Rezept", liste, "BlaBlaBla"));
            recipeRepository.save(new Recipe("Zweites Rezept", liste, "BlubBlubBlub"));

        }
    }
}