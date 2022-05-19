package com.example.cookncalc.recipeIngredient;

import com.example.cookncalc.recipe.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Long> {
    List<RecipeIngredient> findAll();

    List<RecipeIngredient> findAllByRecipeId(Long id);

    List<RecipeIngredient> findByRecipe(Recipe recipe);
}
