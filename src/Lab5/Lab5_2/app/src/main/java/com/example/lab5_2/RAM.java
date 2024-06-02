package com.example.lab5_2;

public class RAM {
    private String name;
    private String description;
    private String brand;
    private String imageURL;
    private int image;

    public RAM(String name, String description, String brand, String imageURL) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.imageURL = imageURL;
    }


    public RAM(String name, String description, String brand, int image) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}