package com.example.cookncalc.recipeIngredient;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Long> {

}
