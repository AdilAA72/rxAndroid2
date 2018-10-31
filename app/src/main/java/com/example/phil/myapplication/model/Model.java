package com.example.phil.myapplication.model;

public class Model {
    private String name;
    private String image;

    public Model(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Model() {
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
