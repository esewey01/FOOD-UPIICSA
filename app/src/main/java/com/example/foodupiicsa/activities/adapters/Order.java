package com.example.foodupiicsa.activities.adapters;

import java.util.List;

public class Order {
    private String name;
    private String price;
    private String rating;

    public Order() {
        // Constructor vacío requerido por Firebase
    }

    public Order(String name, String price, String rating) {
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getRating() {
        return rating;
    }
}

