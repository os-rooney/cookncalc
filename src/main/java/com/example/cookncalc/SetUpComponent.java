package com.example.cookncalc;

import com.example.cookncalc.ingredient.Ingredient;
import com.example.cookncalc.ingredient.IngredientRepository;
import com.example.cookncalc.recipeIngredient.RecipeIngredient;
import com.example.cookncalc.recipeIngredient.RecipeIngredientRepository;
import com.example.cookncalc.recipe.Recipe;
import com.example.cookncalc.recipe.RecipeRepository;
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
        Recipe wurstsalat = new Recipe("Schweizer Wurstsalat", "Ergänzt deine Brotzeit!",
                "Die Fleischwurst, die Zwiebeln und den Käse (am besten Emmentaler) kleinschneiden. " +
                        "Alles in eine Schüssel geben und mit Essig und Öl begießen. Mindestens 3 Stunden ziehen" +
                        " lassen und ggf. vor dem Servieren kaltstellen."
        );
        Recipe gefuellteZucchini = new Recipe("Gefüllte Zucchini", "Ein tolles Ofengericht!",
                "Den Reis kochen. Zwiebeln und Tomaten in kleine Stücke schneiden. Die Zucchinis mit " +
                        "einem Löffel aushöhlen. Hackfleisch nach Belieben würzen und zusammen mit den Zwiebeln " +
                        "anbraten. Reis und Tomaten zufügen und Zucchinis mit der Mischung füllen. Ab in den Ofen " +
                        "und bei 200°C für eine halbe Stunde backen. Nach etwa der Hälfte der Zeit den geriebenen " +
                        "Käse darüber streuen.");

        Recipe schokoTorte = new Recipe("Schokotorte", "Die leckerste Kalorienbombe der Welt!", "Die 150 g Kinderschokolade " +
                " und 250 g Nutella in einem Wasserbad schmelzen. Die Butter cremig rühren und Zucker, den Kakao, die Eigelbe, die Eier, " +
                "den Vanillinzucker und Schokolade unterrühren. Mehl mit Backpulver mischen, Eiweiße steif schlagen. "+
                "Die Teigmasse in zwei Hälften teilen und im vorgeheizten Backofen bei 180°C ca. 40 – 45 min in der Springform backen.");

        Recipe spargelImUltra = new Recipe("Spargel im Ultra", "Das Gericht der Spargelsaison", "Den Spargel gut schälen, die " +
                "holzigen Enden abschneiden und den Spargel tropfnass in den Ultra - Plus von Tupper legen (ich habe den 3-Liter Topf genommen)." +
                " Salz und Zucker darüber streuen und die zerlassene Butter darüber gießen. Den Backofen bei Umluft auf 180°C vorheizen und den " +
                "Spargel darin 40 bis 45 Minuten garen (zwischendurch evtl. eine Messerprobe machen).");


        if (recipeRepository.count() == 0) {
            recipeRepository.save(gemuesepfanne);
            recipeRepository.save(wurstsalat);
            recipeRepository.save(gefuellteZucchini);
            recipeRepository.save(schokoTorte);
            recipeRepository.save(spargelImUltra);
        }

        //new ingredients
        Ingredient basilikum = new Ingredient("Basilikum", "Bund", 1);
        Ingredient kaese = new Ingredient("Käse", "g", 200);
        Ingredient nudeln = new Ingredient("Nudeln", "g", 500);
        Ingredient oel = new Ingredient("Öl", "ml", 500);
        Ingredient tomate = new Ingredient("Tomate(n)", "Stück", 1);
        Ingredient zucchini = new Ingredient("Zucchini", "Stück", 1);

        Ingredient zwiebel = new Ingredient("Zwiebel(n)", "Stück", 1);
        Ingredient wurst = new Ingredient("Fleischwurst", "g", 500);
        Ingredient essig = new Ingredient("Essig", "ml", 500);

        Ingredient hack = new Ingredient("Hackfleisch", "g", 250);
        Ingredient reis = new Ingredient("Reis", "g", 500);

        Ingredient butter = new Ingredient("Butter", "g", 250);
        Ingredient zucker = new Ingredient("Zucker", "g", 500);
        Ingredient vanilleZucker = new Ingredient("Vanillezucker", "Päckchen", 1);
        Ingredient schokolade = new Ingredient("Schokolade", "g", 250);
        Ingredient eier = new Ingredient("Ei(er)", "Stück", 10);
        Ingredient mehl = new Ingredient("Mehl", "g", 500);
        Ingredient kakaoPulver = new Ingredient("Kakaopulver", "g", 100);
        Ingredient backPulver = new Ingredient("Backpulver", "Päckchen", 1);
        Ingredient sahne = new Ingredient("Sahne", "Becher", 1);
        Ingredient nutella = new Ingredient("Nutella", "Glas", 1);
        Ingredient schokoRiegel = new Ingredient("Schokoriegel", "Stück", 10);

        Ingredient Spargel = new Ingredient("Spargel", "Bund", 1);
        Ingredient salz = new Ingredient("Salz", "g", 250);

        if (ingredientRepository.count() == 0) {
            ingredientRepository.save(basilikum);
            ingredientRepository.save(kaese);
            ingredientRepository.save(nudeln);
            ingredientRepository.save(oel);
            ingredientRepository.save(tomate);
            ingredientRepository.save(zucchini);

            ingredientRepository.save(zwiebel);
            ingredientRepository.save(wurst);
            ingredientRepository.save(essig);

            ingredientRepository.save(hack);
            ingredientRepository.save(reis);

            ingredientRepository.save(butter);
            ingredientRepository.save(zucker);
            ingredientRepository.save(vanilleZucker);
            ingredientRepository.save(schokolade);
            ingredientRepository.save(eier);
            ingredientRepository.save(mehl);
            ingredientRepository.save(kakaoPulver);
            ingredientRepository.save(backPulver);
            ingredientRepository.save(sahne);
            ingredientRepository.save(nutella);
            ingredientRepository.save(schokoRiegel);

            ingredientRepository.save(Spargel);
            ingredientRepository.save(salz);
        }

        //link recipes to ingredients
        if (recipeIngredientRepository.count() == 0) {
            addRecipeIngredient(gemuesepfanne, basilikum, 1.0);
            addRecipeIngredient(gemuesepfanne, kaese, 150.0);
            addRecipeIngredient(gemuesepfanne, nudeln, 200.0);
            addRecipeIngredient(gemuesepfanne, oel, 20.0);
            addRecipeIngredient(gemuesepfanne, tomate, 4.0);
            addRecipeIngredient(gemuesepfanne, zucchini, 2.0);
            addRecipeIngredient(gemuesepfanne, salz, 5.0);

            addRecipeIngredient(wurstsalat, zwiebel, 1.0);
            addRecipeIngredient(wurstsalat, wurst, 250.0);
            addRecipeIngredient(wurstsalat, oel, 50.0);
            addRecipeIngredient(wurstsalat, essig, 50.0);
            addRecipeIngredient(wurstsalat, kaese, 200.0);

            addRecipeIngredient(gefuellteZucchini, hack, 150.0);
            addRecipeIngredient(gefuellteZucchini, kaese, 100.0);
            addRecipeIngredient(gefuellteZucchini, reis, 150.0);
            addRecipeIngredient(gefuellteZucchini, tomate, 3.0);
            addRecipeIngredient(gefuellteZucchini, zucchini, 4.0);
            addRecipeIngredient(gefuellteZucchini, zwiebel, 2.0);

            addRecipeIngredient(schokoTorte, butter, 150.0);
            addRecipeIngredient(schokoTorte, zucker, 150.0);
            addRecipeIngredient(schokoTorte, vanilleZucker, 1.0);
            addRecipeIngredient(schokoTorte, schokolade, 600.0);
            addRecipeIngredient(schokoTorte, eier, 8.0);
            addRecipeIngredient(schokoTorte, mehl, 300.0);
            addRecipeIngredient(schokoTorte, kakaoPulver, 400.0);
            addRecipeIngredient(schokoTorte, backPulver,3.0);
            addRecipeIngredient(schokoTorte, sahne,2.0);
            addRecipeIngredient(schokoTorte, nutella, 1.0);
            addRecipeIngredient(schokoTorte, schokoRiegel, 8.0);

            addRecipeIngredient(spargelImUltra, Spargel, 1.0);
            addRecipeIngredient(spargelImUltra, salz, 10.0);
            addRecipeIngredient(spargelImUltra, butter, 30.0);
            addRecipeIngredient(spargelImUltra, zucker, 5.0);
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
            addSupermarketIngredient(basilikum, rewe, 1.0);
            addSupermarketIngredient(basilikum, lidl, 0.8);
            addSupermarketIngredient(basilikum, edeka, 0.9);
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
            addSupermarketIngredient(tomate, rewe, 0.40);
            addSupermarketIngredient(tomate, lidl, 0.30);
            addSupermarketIngredient(tomate, edeka, 0.40);
            //Zucchini
            addSupermarketIngredient(zucchini, rewe, 0.6);
            addSupermarketIngredient(zucchini, lidl, 0.5);
            addSupermarketIngredient(zucchini, edeka, 0.5);

            //Zwiebel
            addSupermarketIngredient(zwiebel, rewe, 0.3);
            addSupermarketIngredient(zwiebel, lidl, 0.2);
            addSupermarketIngredient(zwiebel, edeka, 0.2);
            //Wurst
            addSupermarketIngredient(wurst, rewe, 7.6);
            addSupermarketIngredient(wurst, lidl, 5.35);
            addSupermarketIngredient(wurst, edeka, 6.5);
            //Essig
            addSupermarketIngredient(essig, rewe, 5.6);
            addSupermarketIngredient(essig, lidl, 4.35);
            addSupermarketIngredient(essig, edeka, 5.1);

            //Hack
            addSupermarketIngredient(hack, rewe, 7.0);
            addSupermarketIngredient(hack, lidl, 6.5);
            addSupermarketIngredient(hack, edeka, 8.25);
            //Reis
            addSupermarketIngredient(reis, rewe, 1.80);
            addSupermarketIngredient(reis, lidl, 1.60);
            addSupermarketIngredient(reis, edeka, 1.70);

            //Butter
            addSupermarketIngredient(butter, rewe, 1.50);
            addSupermarketIngredient(butter, lidl, 0.80);
            addSupermarketIngredient(butter, edeka, 1.25);
            //Zucker
            addSupermarketIngredient(zucker, rewe, 0.70);
            addSupermarketIngredient(zucker, lidl, 0.30);
            addSupermarketIngredient(zucker, edeka, 0.65);
            //VanilleZucker
            addSupermarketIngredient(vanilleZucker, rewe, 0.50);
            addSupermarketIngredient(vanilleZucker, lidl, 0.20);
            addSupermarketIngredient(vanilleZucker, edeka, 0.45);
            //Schokolade
            addSupermarketIngredient(schokolade, rewe, 2.70);
            addSupermarketIngredient(schokolade, lidl, 2.00);
            addSupermarketIngredient(schokolade, edeka, 2.35);
            //Eier
            addSupermarketIngredient(eier, rewe, 2.59);
            addSupermarketIngredient(eier, lidl, 1.99);
            addSupermarketIngredient(eier, edeka, 2.20);
            //Mehl
            addSupermarketIngredient(mehl, rewe, 0.90);
            addSupermarketIngredient(mehl, lidl, 0.60);
            addSupermarketIngredient(mehl, edeka, 0.75);
            //Kakaopulver
            addSupermarketIngredient(kakaoPulver, rewe, 2.20);
            addSupermarketIngredient(kakaoPulver, lidl, 2.00);
            addSupermarketIngredient(kakaoPulver, edeka, 2.15);
            //backPulver
            addSupermarketIngredient(backPulver, rewe, 1.20);
            addSupermarketIngredient(backPulver, lidl, 1.00);
            addSupermarketIngredient(backPulver, edeka, 1.66);
            //Sahne
            addSupermarketIngredient(sahne, rewe, 1.70);
            addSupermarketIngredient(sahne, lidl, 1.00);
            addSupermarketIngredient(sahne, edeka, 1.35);
            //Nutella
            addSupermarketIngredient(nutella, rewe, 3.50);
            addSupermarketIngredient(nutella, lidl, 3.20);
            addSupermarketIngredient(nutella, edeka, 3.65);
            //Schokoriegel
            addSupermarketIngredient(schokoRiegel, rewe, 2.95);
            addSupermarketIngredient(schokoRiegel, lidl, 2.15);
            addSupermarketIngredient(schokoRiegel, edeka, 2.55);

            //Spargel
            addSupermarketIngredient(Spargel, rewe, 4.50);
            addSupermarketIngredient(Spargel, lidl, 3.80);
            addSupermarketIngredient(Spargel, edeka, 4.25);
            //Salz
            addSupermarketIngredient(salz, rewe, 1.30);
            addSupermarketIngredient(salz, lidl, 0.90);
            addSupermarketIngredient(salz, edeka, 1.15);
        }
    }
}