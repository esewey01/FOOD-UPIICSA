package com.example.foodupiicsa.activities.models;

public class DetalleDiarioModel {
    int image;
    String nombre;
    String descripcion;
    String rating;
    String precio;
    String tiempo;

    public DetalleDiarioModel(int image, String nombre, String descripcion, String rating, String precio, String tiempo) {
        this.image = image;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.rating = rating;
        this.precio = precio;
        this.tiempo = tiempo;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
}
