package com.example.foodupiicsa.activities.models;

import android.util.Log;

public class CartModel {
    int imageResource, image;
    String name;
    String price;
    String rating;

    public CartModel(int image, String name, String price, String rating) {
        this.imageResource = imageResource;
        this.name = name;
        this.price = price;
        this.rating = rating;

        //log para verificar los valores al creal el objeto
        Log.d("CartModel","CartModel creado"
                +"image: "+image+", name: "+name+
                ", price: "+price+", rating: "+rating);

    }

    public int getImage() {
        return image;
    }


    public int getImageResource() {

        return imageResource;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CartModel that = (CartModel) obj;
        return name.equals(that.name); // Compara por nombre o cualquier propiedad única.
    }

    @Override
    public int hashCode() {
        return name.hashCode(); // Usa la misma propiedad única.
    }

}
