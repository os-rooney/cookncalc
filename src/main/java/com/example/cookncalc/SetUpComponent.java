package com.example.cookncalc;

import com.example.cookncalc.ingredient.Ingredient;
import com.example.cookncalc.ingredient.IngredientRepository;
import com.example.cookncalc.recipeIngredient.RecipeIngredient;
import com.example.cookncalc.recipeIngredient.RecipeIngredientRespository;
import com.example.cookncalc.recipes.Recipe;
import com.example.cookncalc.recipes.RecipeRepository;
import com.example.cookncalc.supermarket.Supermarket;
import com.example.cookncalc.supermarket.SupermarketRepository;
import com.example.cookncalc.supermarketIngredient.SupermarketIngredient;
import com.example.cookncalc.supermarketIngredient.SupermarketIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SetUpComponent {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRespository recipeIngredientRespository;
    private final SupermarketIngredientRepository supermarketIngredientRepository;
    private final SupermarketRepository supermarketRepository;

    @Autowired
    public SetUpComponent(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, RecipeIngredientRespository recipeIngredientRespository, SupermarketIngredientRepository supermarketIngredientRepository, SupermarketRepository supermarketRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRespository = recipeIngredientRespository;
        this.supermarketIngredientRepository = supermarketIngredientRepository;
        this.supermarketRepository = supermarketRepository;
    }

    @EventListener
    @Transactional
    public void handleApplicationReady(ApplicationReadyEvent event) {
        Recipe recipe = new Recipe("Kuchen", "Bester Kuchen");
        Ingredient butter = new Ingredient("Butter", "gramm", 500 );
        Ingredient marmelade = new Ingredient("Marmelade", "gramm", 500);
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setRecipe(recipe);
        recipeIngredient.setIngredient(butter);
        recipeIngredient.setAmount(3);

        RecipeIngredient recipeIngredient1 = new RecipeIngredient();
        recipeIngredient1.setRecipe(recipe);
        recipeIngredient1.setIngredient(marmelade);
        recipeIngredient1.setAmount(2);

        if (recipeRepository.count() == 0) {
            recipeRepository.save(recipe);
            ingredientRepository.save(butter);
            ingredientRepository.save(marmelade);
            recipeIngredientRespository.save(recipeIngredient);
            recipeIngredientRespository.save(recipeIngredient1);
        }

        Supermarket rewe = new Supermarket("Rewe");
        Supermarket lidl = new Supermarket("Lidl");
        Supermarket edeka = new Supermarket("Edeka");
        if (supermarketRepository.count() == 0) {
            supermarketRepository.save(rewe);
            supermarketRepository.save(lidl);
            supermarketRepository.save(edeka);
        }

        if (supermarketIngredientRepository.count() == 0) {
            SupermarketIngredient supermarketIngredientRewe = new SupermarketIngredient();
            supermarketIngredientRewe.setIngredient(butter);
            supermarketIngredientRewe.setSupermarket(rewe);
            supermarketIngredientRewe.setPrice(3.0);
            supermarketIngredientRepository.save(supermarketIngredientRewe);

            SupermarketIngredient supermarketIngredientLidl = new SupermarketIngredient();
            supermarketIngredientLidl.setIngredient(butter);
            supermarketIngredientLidl.setSupermarket(lidl);
            supermarketIngredientLidl.setPrice(2.5);
            supermarketIngredientRepository.save(supermarketIngredientLidl);

            SupermarketIngredient supermarketIngredientEdeka = new SupermarketIngredient();
            supermarketIngredientEdeka.setIngredient(butter);
            supermarketIngredientEdeka.setSupermarket(edeka);
            supermarketIngredientEdeka.setPrice(2.75);
            supermarketIngredientRepository.save(supermarketIngredientEdeka);

            //Marmelade
            SupermarketIngredient supermarketMarmeladeRewe = new SupermarketIngredient();
            supermarketMarmeladeRewe.setIngredient(marmelade);
            supermarketMarmeladeRewe.setSupermarket(rewe);
            supermarketMarmeladeRewe.setPrice(3.0);
            supermarketIngredientRepository.save(supermarketMarmeladeRewe);

            SupermarketIngredient supermarketMarmeladeLidl = new SupermarketIngredient();
            supermarketMarmeladeLidl.setIngredient(marmelade);
            supermarketMarmeladeLidl.setSupermarket(lidl);
            supermarketMarmeladeLidl.setPrice(2.0);
            supermarketIngredientRepository.save(supermarketMarmeladeLidl);

            SupermarketIngredient supermarketMarmeladeEdeka = new SupermarketIngredient();
            supermarketMarmeladeEdeka.setIngredient(marmelade);
            supermarketMarmeladeEdeka.setSupermarket(edeka);
            supermarketMarmeladeEdeka.setPrice(1.90);
            supermarketIngredientRepository.save(supermarketMarmeladeEdeka);
        }
    }
}