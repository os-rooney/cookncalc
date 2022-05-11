package com.example.cookncalc.supermarketIngredient;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupermarketIngredientRepository extends CrudRepository <SupermarketIngredient, Long> {
}
