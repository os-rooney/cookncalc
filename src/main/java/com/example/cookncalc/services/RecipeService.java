package com.example.cookncalc.services;

import com.example.cookncalc.json.RecipeWithIngredientsDTO;
import com.example.cookncalc.ingredient.Ingredient;
import com.example.cookncalc.ingredient.IngredientDTO;
import com.example.cookncalc.ingredient.IngredientRepository;
import com.example.cookncalc.recipeIngredient.RecipeIngredient;
import com.example.cookncalc.recipeIngredient.RecipeIngredientRepository;
import com.example.cookncalc.recipeIngredient.RecipeIngredientRespository;
import com.example.cookncalc.recipes.Recipe;
import com.example.cookncalc.recipes.RecipeDTO;
import com.example.cookncalc.recipes.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRespository recipeIngredientRespository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository,
                         IngredientRepository ingredientRepository,
                         RecipeIngredientRespository recipeIngredientRespository, RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRespository = recipeIngredientRespository;
        this.recipeIngredientRepository = recipeIngredientRepository;
    }


    public List<RecipeDTO> showRecipes(){
        List<RecipeDTO> recipeDTO = new LinkedList<RecipeDTO>();
        List<Recipe> recipes = recipeRepository.findAll();
        for(Recipe recipe : recipes){
            RecipeDTO recipeDTO1 =  new RecipeDTO();
            recipeDTO1.setId(recipe.getId());
            recipeDTO1.setTitle(recipe.getTitle());
            recipeDTO1.setDescription(recipe.getDescription());
            recipeDTO.add(recipeDTO1);
        }
        return recipeDTO;
    }

    public RecipeWithIngredientsDTO showDetailRecipe(Long id) {
        RecipeWithIngredientsDTO recipeWithIngredientsDTO = new RecipeWithIngredientsDTO();
        Recipe recipe = recipeRepository.findById(id).orElseThrow();
        recipeWithIngredientsDTO.setTitle(recipe.getTitle());
        recipeWithIngredientsDTO.setDescription(recipe.getDescription());
        recipeWithIngredientsDTO.setPreparation(recipe.getPreparation());

        //Für ein bst. Recipe, Ingredients (Id's der Ingredients), Amounts von jedem Ingredient
        //Amounts --> in Liste von Ingredient DTO'S
        List<RecipeIngredient> recipeIngredient = recipeIngredientRespository.findAllByRecipeId(recipe.getId());



        for(RecipeIngredient recipeIngredient1: recipeIngredient){
            IngredientDTO ingredientDTO = new IngredientDTO();
            ingredientDTO.setAmount(recipeIngredient1.getAmount());
            ingredientDTO.setName(recipeIngredient1.getIngredient().getName());
            ingredientDTO.setUnit(recipeIngredient1.getIngredient().getUnit());
            recipeWithIngredientsDTO.getIngredients().add(ingredientDTO);
        }

        return recipeWithIngredientsDTO;

    }


    public void addRecipe(RecipeWithIngredientsDTO dto){
        Recipe recipe = new Recipe();
        recipe.setTitle(dto.getTitle());
        recipe.setDescription(dto.getDescription());
        recipe.setPreparation(dto.getPreparation());
        recipeRepository.save(recipe);

        for(IngredientDTO ingredientDTO : dto.getIngredients()){
            Double amount = ingredientDTO.getAmount();
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setRecipe(recipe);
            Ingredient ingredient = new Ingredient(ingredientDTO.getName(), ingredientDTO.getUnit());
            recipeIngredient.setIngredient(ingredient);
            recipeIngredient.setAmount(amount);
            ingredientRepository.save(ingredient);
            recipeIngredientRespository.save(recipeIngredient);
        }
    }


    public List<RecipeDTO> deleteRecipe(Long id){
        Optional<Recipe> recipeToDeleteOptional = recipeRepository.findById(id);
        if(!recipeToDeleteOptional.isPresent()){
            return null;
        }
        Recipe recipeToDelete = recipeToDeleteOptional.get();
        List<RecipeIngredient> recipeIngredient = recipeIngredientRepository.findByRecipe(recipeToDelete);
        for(RecipeIngredient recipeIngredient1: recipeIngredient){
            recipeIngredientRepository.delete(recipeIngredient1);
        }
        recipeRepository.delete(recipeToDelete);

        return showRecipes();
    }

}