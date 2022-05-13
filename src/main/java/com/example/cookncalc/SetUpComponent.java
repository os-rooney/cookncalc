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
        //new recipes
        Recipe gemuesepfanne = new Recipe("Mediterrane Zucchinipfanne", "Schmeckt mit jedem Gemüse ;)",
                "Nudelwasser aufsetzen und kräftig salzen. Das Gemüse waschen und in kleine Würfel" +
                        " schneiden. Zucchini schon mal salzen, damit sie Wasser ziehen können. Käse ebenfalls" +
                        " würfeln. Besonders gut eignet sich Feta, aber wer es beispielsweise lieber italienisch " +
                        "mag, kann auch Mozzarella oder Parmesan (dann aber gerieben) nehmen. Wenn das Wasser kocht," +
                        " Nudeln zugeben - echte Pasta muss übrigens ganz ohne Öl im Nudelwasser auskommen! " +
                        "Zucchiniwürfel mit etwas Olivenöl in eine Pfanne geben. Wenn die Nudeln al dente sind," +
                        " das Wasser abgießen und ebenfalls in die Pfanne geben. Nachdem die Nudeln Farbe bekommen" +
                        " haben, auch die mit Salz und Pfeffer gewürzten Tomaten dazugeben. Die frischen " +
                        "Basilikumblätter nicht schneiden, sonder in Stücke zupfen, damit sich das Aroma entfalten" +
                        " kann. Zuletzt wird noch der Feta über die Pfanne gestreut und dann kann serviert werden :)"
        );
        Recipe gefuellteZucchini = new Recipe("gefüllte Zucchini", "Ein tolles Ofengericht!",
                "Den Reis kochen. Zwiebeln und Tomaten in kleine Stücke schneiden. Die Zucchinis mit " +
                        "einem Löffel aushöhlen. Hackfleisch nach Belieben würzen und zusammen mit den Zwiebeln " +
                        "anbraten. Reis und Tomaten zufügen und Zucchinis mit der Mischung füllen. Ab in den Ofen " +
                        "und bei 200°C für eine halbe Stunde backen. Nach etwa der Hälfte der Zeit den geriebenen " +
                        "Käse darüber streuen.");

        if (recipeRepository.count() == 0) {
            recipeRepository.save(gemuesepfanne);
            recipeRepository.save(gefuellteZucchini);
        }

        //new ingredients
        Ingredient basilikum = new Ingredient("Basilikum", "Bund", 1);
        Ingredient kaese = new Ingredient("Käse", "g", 200);
        Ingredient nudeln = new Ingredient("Nudeln", "g", 500);
        Ingredient oel = new Ingredient("Öl", "ml", 500);
        Ingredient tomate = new Ingredient("Tomate(n)", "Stück", 1);
        Ingredient zucchini = new Ingredient("Zucchini", "Stück", 1);

        Ingredient hack = new Ingredient("Hackfleisch", "g", 250);
        Ingredient reis = new Ingredient("Reis", "g", 500);
        Ingredient zwiebel = new Ingredient("Zwiebel(n)", "Stück", 1);

//        Ingredient butter = new Ingredient("Butter", "g", 250);
//        Ingredient marmelade = new Ingredient("Marmelade", "g", 500);
//        Ingredient mehl = new Ingredient("Mehl", "g", 1000);
//        Ingredient eier = new Ingredient("Eier", "Stück", 10);

        if (ingredientRepository.count() == 0) {
            ingredientRepository.save(basilikum);
            ingredientRepository.save(kaese);
            ingredientRepository.save(nudeln);
            ingredientRepository.save(oel);
            ingredientRepository.save(tomate);
            ingredientRepository.save(zucchini);

            ingredientRepository.save(hack);
            ingredientRepository.save(reis);
            ingredientRepository.save(zwiebel);

//            ingredientRepository.save(butter);
//            ingredientRepository.save(marmelade);
//            ingredientRepository.save(mehl);
//            ingredientRepository.save(eier);
        }

        //link recipes to ingredients
        if (recipeIngredientRepository.count() == 0) {
            addRecipeIngredient(gemuesepfanne, basilikum, 1.0);
            addRecipeIngredient(gemuesepfanne, kaese, 150.0);
            addRecipeIngredient(gemuesepfanne, nudeln, 200.0);
            addRecipeIngredient(gemuesepfanne, oel, 20.0);
            addRecipeIngredient(gemuesepfanne, tomate, 4.0);
            addRecipeIngredient(gemuesepfanne, zucchini, 2.0);

            addRecipeIngredient(gefuellteZucchini, hack, 150.0);
            addRecipeIngredient(gefuellteZucchini, kaese, 100.0);
            addRecipeIngredient(gefuellteZucchini, reis, 150.0);
            addRecipeIngredient(gefuellteZucchini, tomate, 3.0);
            addRecipeIngredient(gefuellteZucchini, zucchini, 4.0);
            addRecipeIngredient(gefuellteZucchini, zwiebel, 2.0);
        }


        //new supermarket
        Supermarket rewe = new Supermarket("Rewe");
        Supermarket lidl = new Supermarket("Lidl");
        Supermarket edeka = new Supermarket("Edeka");

        if (supermarketRepository.count() == 0) {
            supermarketRepository.save(rewe);
            supermarketRepository.save(lidl);
            supermarketRepository.save(edeka);
        }

        //link recipes to ingredients
        if (supermarketIngredientRepository.count() == 0) {
            //Basilikum
            addSupermarketIngredient(basilikum, rewe, 2.0);
            addSupermarketIngredient(basilikum, lidl, 1.5);
            addSupermarketIngredient(basilikum, edeka, 1.8);
            //Käse
            addSupermarketIngredient(kaese, rewe, 3.0);
            addSupermarketIngredient(kaese, lidl, 2.3);
            addSupermarketIngredient(kaese, edeka, 2.5);
            //Nudeln
            addSupermarketIngredient(nudeln, rewe, 2.0);
            addSupermarketIngredient(nudeln, lidl, 1.50);
            addSupermarketIngredient(nudeln, edeka, 1.75);
            //Öl
            addSupermarketIngredient(oel, rewe, 5.0);
            addSupermarketIngredient(oel, lidl, 5.5);
            addSupermarketIngredient(oel, edeka, 5.25);
            //Tomate
            addSupermarketIngredient(tomate, rewe, 0.80);
            addSupermarketIngredient(tomate, lidl, 0.60);
            addSupermarketIngredient(tomate, edeka, 0.70);
            //Zucchini
            addSupermarketIngredient(zucchini, rewe, 1.5);
            addSupermarketIngredient(zucchini, lidl, 1.4);
            addSupermarketIngredient(zucchini, edeka, 1.5);

            //Hack
            addSupermarketIngredient(hack, rewe, 7.0);
            addSupermarketIngredient(hack, lidl, 6.5);
            addSupermarketIngredient(hack, edeka, 8.25);
            //Reis
            addSupermarketIngredient(reis, rewe, 1.80);
            addSupermarketIngredient(reis, lidl, 1.60);
            addSupermarketIngredient(reis, edeka, 1.70);
            //Zwiebel
            addSupermarketIngredient(zwiebel, rewe, 0.6);
            addSupermarketIngredient(zwiebel, lidl, 0.35);
            addSupermarketIngredient(zwiebel, edeka, 0.5);

//            //Butter
//            addSupermarketIngredient(butter, rewe, 3.0);
//            addSupermarketIngredient(butter, lidl, 2.50);
//            addSupermarketIngredient(butter, edeka, 2.75);
//            //Marmelade
//            addSupermarketIngredient(marmelade, rewe, 3.0);
//            addSupermarketIngredient(marmelade, lidl, 2.0);
//            addSupermarketIngredient(marmelade, edeka, 1.90);
//            //Mehl
//            addSupermarketIngredient(mehl, rewe, 1.79);
//            addSupermarketIngredient(mehl, lidl, 1.29);
//            addSupermarketIngredient(mehl, edeka, 1.20);
//            //Eier
//            addSupermarketIngredient(eier, rewe, 2.59);
//            addSupermarketIngredient(eier, lidl, 1.99);
//            addSupermarketIngredient(eier, edeka, 2.20);
        }
    }
}