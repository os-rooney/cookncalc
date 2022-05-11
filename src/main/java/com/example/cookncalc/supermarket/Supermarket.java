package com.example.cookncalc.supermarket;

import com.example.cookncalc.supermarketIngredient.SupermarketIngredient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Supermarket {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany (mappedBy = "supermarket")
    private Set<SupermarketIngredient> supermarketIngredients = new HashSet<>();

    public Supermarket() {
    }

    public Supermarket(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
