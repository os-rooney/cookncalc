package com.example.cookncalc.supermarketIngredient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class SupermarketIngredientController {



    private final SupermarketIngredientRepository supermarketIngredientRepository;

    public SupermarketIngredientController(SupermarketIngredientRepository supermarketIngredientRepository) {
        this.supermarketIngredientRepository = supermarketIngredientRepository;
    }

    @GetMapping("/api/prices/{id}")
    public List<SupermarketIngredient> getIngredientPrice(@PathVariable("id") Long id){
        return supermarketIngredientRepository.findSupermarketIngredientByIngredientId(id);
    }
}
