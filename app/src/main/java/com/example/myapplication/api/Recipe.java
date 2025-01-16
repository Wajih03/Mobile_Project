package com.example.myapplication.api;

public class Recipe {
    private String name;
    private String time;
    private String ingredients;
    private String description;
    private String image; // Image URL as a String

    // Constructor
    public Recipe(String name, String time, String ingredients, String description, String image) {
        this.name = name;
        this.time = time;
        this.ingredients = ingredients;
        this.description = description;
        this.image = image;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
