package com.example.foodupiicsa.activities.models;

import android.nfc.cardemulation.HostApduService;

public class HomeVerModel {
    int image;
    String name;
    String timing;
    String rating;
    String price;

    public HomeVerModel(int image, String name, String timing, String rating, String price) {
        this.image = image;
        this.name = name;
        this.timing = timing;
        this.rating = rating;
        this.price = price;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        HomeVerModel that = (HomeVerModel) obj;
        return name.equals(that.name);
    }
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
