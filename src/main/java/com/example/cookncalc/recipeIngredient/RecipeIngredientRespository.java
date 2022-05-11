package com.example.cookncalc.recipeIngredient;

import com.example.cookncalc.recipes.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeIngredientRespository extends CrudRepository<RecipeIngredient, RecipeIngredientKey> {
    List<RecipeIngredient> findAll();
}
