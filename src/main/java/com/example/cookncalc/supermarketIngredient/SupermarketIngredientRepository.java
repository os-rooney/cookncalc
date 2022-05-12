package com.example.cookncalc.supermarketIngredient;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SupermarketIngredientRepository extends CrudRepository <SupermarketIngredient, Long> {
    List<SupermarketIngredient> findSupermarketIngredientByIngredientId(Long id);
   // List<SupermarketIngredient> findAllByIngredientId(Long id);
}
