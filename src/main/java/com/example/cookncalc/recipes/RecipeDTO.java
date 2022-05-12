package com.example.cookncalc.recipes;


public class RecipeDTO {

    private Long id;
    private String title;
    private String description;
    private String preparation;

    public RecipeDTO(){}

    public RecipeDTO(String title, String description, String preparation) {
        this.title = title;
        this.description = description;
        this.preparation = preparation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
