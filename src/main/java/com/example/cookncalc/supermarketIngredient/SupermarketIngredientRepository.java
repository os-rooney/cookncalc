package com.example.cookncalc.supermarketIngredient;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupermarketIngredientRepository extends CrudRepository <SupermarketIngredient, Long> {
    List<SupermarketIngredient> findSupermarketIngredientByIngredientId(Long id);
}
