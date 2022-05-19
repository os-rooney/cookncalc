package com.example.cookncalc.ingredient;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    List<Ingredient> findAll();

    Optional<Ingredient> findByName(String name);

    Optional<Ingredient> findByUnit(String unit);
}
