package com.example.foodupiicsa.activities.models;

public class Order {
    private String name;
    private String price;
    private String rating;

    public Order() {
        // Constructor vacío requerido por Firebase
    }

    public Order(String name, String price) {
        this.name = name;
        this.price = price;
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

