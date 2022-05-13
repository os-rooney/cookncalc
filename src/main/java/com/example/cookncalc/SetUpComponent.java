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

    public void supermarketIngredientService(Ingredient ingredient, Supermarket supermarket, Double price ){
        SupermarketIngredient supermarketIngredient = new SupermarketIngredient();
        supermarketIngredient.setIngredient(ingredient);
        supermarketIngredient.setSupermarket(supermarket);
        supermarketIngredient.setPrice(price);
        supermarketIngredientRepository.save(supermarketIngredient);
    }

    @EventListener
    @Transactional
    public void handleApplicationReady(ApplicationReadyEvent event) {
        Recipe kuchen = new Recipe("Kuchen", "Bester Kuchen");
        Recipe cookies = new Recipe("Cookies", "Beste Cookies der Welt");

        Ingredient butter = new Ingredient("Butter", "gramm", 250 );
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

        RecipeIngredient cookieMehl = new RecipeIngredient();
        cookieMehl.setRecipe(cookies);
        cookieMehl.setIngredient(mehl);
        cookieMehl.setAmount(550);

        RecipeIngredient cookieButter = new RecipeIngredient();
        cookieButter.setRecipe(cookies);
        cookieButter.setIngredient(butter);
        cookieButter.setAmount(250);

        RecipeIngredient cookieEi = new RecipeIngredient();
        cookieEi.setRecipe(cookies);
        cookieEi.setIngredient(eier);
        cookieEi.setAmount(3);

        if (recipeRepository.count() == 0) {
            recipeRepository.save(kuchen);
            recipeRepository.save(cookies);

            ingredientRepository.save(butter);
            ingredientRepository.save(marmelade);
            ingredientRepository.save(mehl);
            ingredientRepository.save(eier);

            recipeIngredientRespository.save(kuchenButter);
            recipeIngredientRespository.save(kuchenMarmelade);
            recipeIngredientRespository.save(cookieMehl);
            recipeIngredientRespository.save(cookieButter);
            recipeIngredientRespository.save(cookieEi);
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
            SupermarketIngredient supermarketButterRewe = new SupermarketIngredient();
            supermarketButterRewe.setIngredient(butter);
            supermarketButterRewe.setSupermarket(rewe);
            supermarketButterRewe.setPrice(3.0);
            supermarketIngredientRepository.save(supermarketButterRewe);

            SupermarketIngredient supermarketButterLidl = new SupermarketIngredient();
            supermarketButterLidl.setIngredient(butter);
            supermarketButterLidl.setSupermarket(lidl);
            supermarketButterLidl.setPrice(2.5);
            supermarketIngredientRepository.save(supermarketButterLidl);

            SupermarketIngredient supermarketButterEdeka = new SupermarketIngredient();
            supermarketButterEdeka.setIngredient(butter);
            supermarketButterEdeka.setSupermarket(edeka);
            supermarketButterEdeka.setPrice(2.75);
            supermarketIngredientRepository.save(supermarketButterEdeka);

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

            //Mehl
            supermarketIngredientService(mehl, rewe, 1.79);
            supermarketIngredientService(mehl, lidl, 1.29);
            supermarketIngredientService(mehl, edeka, 1.20);

            //Eier
            supermarketIngredientService(eier, rewe, 2.59);
            supermarketIngredientService(eier, lidl, 1.99);
            supermarketIngredientService(eier, edeka, 2.20);

        }
    }
}