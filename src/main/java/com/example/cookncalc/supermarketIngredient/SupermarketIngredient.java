package com.example.cookncalc.supermarketIngredient;

import com.example.cookncalc.ingredient.Ingredient;
import com.example.cookncalc.supermarket.Supermarket;

import javax.persistence.*;

@Entity
public class SupermarketIngredient {
    @EmbeddedId
    private SupermarketIngredientKey id = new SupermarketIngredientKey();

    @ManyToOne
    @MapsId ("supermarketId")
    @JoinColumn(name = "supermarket_id")
    private Supermarket supermarket;

    @ManyToOne
    @MapsId ("ingredientId")
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private Double price;



    public SupermarketIngredient() {
    }

    public void setId(SupermarketIngredientKey id) {
        this.id = id;
    }

    public SupermarketIngredientKey getId() {
        return id;
    }

    public Supermarket getSupermarket() {
        return supermarket;
    }

    public void setSupermarket(Supermarket supermarket) {
        this.supermarket = supermarket;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
