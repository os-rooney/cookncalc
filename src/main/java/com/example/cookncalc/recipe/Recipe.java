package com.example.cookncalc.recipe;

import com.example.cookncalc.recipeIngredient.RecipeIngredient;
import com.example.cookncalc.user.User;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @OneToMany(mappedBy = "recipe")
    private Set<RecipeIngredient> ingredients = new HashSet<>();

    private String description;

    @Column(columnDefinition = "varchar(1000) default 'Einfach alle Zutaten kombinieren ;)'")
    private String preparation;

    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Recipe(){
        this.createdAt = Instant.now();
    }

    public Recipe(String title, String description, String preparation, User user) {
        this.title = title;
        this.description = description;
        this.preparation = preparation;
        this.createdAt = Instant.now();
        this.user = user;
    }

    public Recipe(String title, String description, String preparation) {
        this.title = title;
        this.description = description;
        this.preparation = preparation;
        this.createdAt = Instant.now();
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public Set<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<RecipeIngredient> recipeIngredient) {
        this.ingredients = recipeIngredient;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
