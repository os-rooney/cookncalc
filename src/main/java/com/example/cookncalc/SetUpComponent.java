package com.example.cookncalc;

import com.example.cookncalc.ingredient.Ingredient;
import com.example.cookncalc.ingredient.IngredientRepository;
import com.example.cookncalc.recipeIngredient.RecipeIngredient;
import com.example.cookncalc.recipeIngredient.RecipeIngredientRepository;
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
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final SupermarketIngredientRepository supermarketIngredientRepository;
    private final SupermarketRepository supermarketRepository;

    @Autowired
    public SetUpComponent(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, RecipeIngredientRepository recipeIngredientRepository, SupermarketIngredientRepository supermarketIngredientRepository, SupermarketRepository supermarketRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.supermarketIngredientRepository = supermarketIngredientRepository;
        this.supermarketRepository = supermarketRepository;
    }

    public void addSupermarketIngredient(Ingredient ingredient, Supermarket supermarket, Double price) {
        SupermarketIngredient supermarketIngredient = new SupermarketIngredient();
        supermarketIngredient.setIngredient(ingredient);
        supermarketIngredient.setSupermarket(supermarket);
        supermarketIngredient.setPrice(price);
        supermarketIngredientRepository.save(supermarketIngredient);
    }

    public void addRecipeIngredient(Recipe recipe, Ingredient ingredient, Double amount) {
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setRecipe(recipe);
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setAmount(amount);
        recipeIngredientRepository.save(recipeIngredient);
    }


    @EventListener
    @Transactional
    public void handleApplicationReady(ApplicationReadyEvent event) {
        Recipe kuchen = new Recipe("Kuchen", "Bester Kuchen");
        Recipe cookies = new Recipe("Cookies", "Beste Cookies der Welt");

        Ingredient butter = new Ingredient("Butter", "gramm", 250);
        Ingredient marmelade = new Ingredient("Marmelade", "gramm", 500);
        Ingredient mehl = new Ingredient("Mehl", "gramm", 1000);
        Ingredient eier = new Ingredient("Eier", "Stück", 10);

        RecipeIngredient kuchenButter = new RecipeIngredient();
        kuchenButter.setRecipe(kuchen);
        kuchenButter.setIngredient(butter);
        kuchenButter.setAmount(3);

        RecipeIngredient kuchenMarmelade = new RecipeIngredient();
        kuchenMarmelade.setRecipe(kuchen);
        kuchenMarmelade.setIngredient(marmelade);
        kuchenMarmelade.setAmount(2);


        if (recipeRepository.count() == 0) {
            recipeRepository.save(kuchen);
            recipeRepository.save(cookies);

            ingredientRepository.save(butter);
            ingredientRepository.save(marmelade);
            ingredientRepository.save(mehl);
            ingredientRepository.save(eier);

            recipeIngredientRepository.save(kuchenButter);
            recipeIngredientRepository.save(kuchenMarmelade);

            addRecipeIngredient(cookies, mehl, 550.0);
            addRecipeIngredient(cookies, eier, 3.0);
            addRecipeIngredient(cookies, butter, 250.0);


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
            addSupermarketIngredient(butter, rewe, 3.0);
            addSupermarketIngredient(butter, lidl, 2.50);
            addSupermarketIngredient(butter, edeka, 2.75);

            //Marmelade
            addSupermarketIngredient(marmelade, rewe, 3.0);
            addSupermarketIngredient(marmelade, lidl, 2.0);
            addSupermarketIngredient(marmelade, edeka, 1.90);

            //Mehl
            addSupermarketIngredient(mehl, rewe, 1.79);
            addSupermarketIngredient(mehl, lidl, 1.29);
            addSupermarketIngredient(mehl, edeka, 1.20);

            //Eier
            addSupermarketIngredient(eier, rewe, 2.59);
            addSupermarketIngredient(eier, lidl, 1.99);
            addSupermarketIngredient(eier, edeka, 2.20);

        }
    }
}