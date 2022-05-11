package com.example.cookncalc.supermarketIngredient;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SupermarketIngredientKey implements Serializable {

    @Column(name="supermarket_Id")
    private Long supermarketId;

    @Column(name = "ingredient_Id")
    private Long ingredientId;

    public SupermarketIngredientKey() {}

    public Long getSupermarketId() {
        return supermarketId;
    }

    public void setSupermarketId(Long supermarketId) {
        this.supermarketId = supermarketId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }
}
