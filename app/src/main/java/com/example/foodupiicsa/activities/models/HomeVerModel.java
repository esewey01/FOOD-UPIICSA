package com.example.foodupiicsa.activities.models;

public class HomeVerModel {
    int image;
    String name;
    String timing;
    String likes;
    String price;

    public HomeVerModel(String name, String likes, String timing, int image){
        this.name = name;
        this.likes = likes;
        this.timing = timing;
        this.image = image;
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

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
