package com.example.phil.myapplication.model;

public class Details {
    public String name ;
    public String text ;
    public String image;

    public Details(String name, String text, String image) {
        this.name = name;
        this.text = text;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

