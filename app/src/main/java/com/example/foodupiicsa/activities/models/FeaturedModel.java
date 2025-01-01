package com.example.foodupiicsa.activities.models;

public class FeaturedModel {
    int image;
    String name;
    String desc;

    public FeaturedModel(int image, String name, String des) {
        this.image = image;
        this.name = name;
        this.desc = des;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return desc;
    }

    public void setDes(String des) {
        this.desc = des;
    }
}
