package com.example.meme.model;

public class Category {
    public int id;
    public String categoryName;
    public String imageName;

    public Category(int id, String categoryName, String imageName) {
        this.id = id;
        this.categoryName = categoryName;
        this.imageName = imageName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
