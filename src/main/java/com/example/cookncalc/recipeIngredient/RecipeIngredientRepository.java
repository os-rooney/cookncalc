package com.example.cookncalc.recipeIngredient;

import com.example.cookncalc.recipes.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Long> {
    List<RecipeIngredient> findByRecipe(Recipe recipe);
}
