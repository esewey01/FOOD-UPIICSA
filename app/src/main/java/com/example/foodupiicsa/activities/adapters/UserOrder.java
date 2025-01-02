package com.example.foodupiicsa.activities.adapters;

import com.example.foodupiicsa.activities.models.Order;

import java.util.List;

// Modelo para los pedidos del usuario
public class UserOrder {
    private String username;
    private String email;
    private String boleta;
    private List<Order> orders;

    public UserOrder() {
        // Constructor vacío requerido por Firebase
    }

    public UserOrder(String username, String email, String boleta, List<Order> orders) {
        this.username = username;
        this.email = email;
        this.boleta = boleta;
        this.orders = orders;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getBoleta() {
        return boleta;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
