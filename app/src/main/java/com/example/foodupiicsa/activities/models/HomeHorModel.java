package com.example.foodupiicsa.activities.models;

// Clase modelo para representar los datos de cada elemento en el RecyclerView horizontal
public class HomeHorModel {
    // Variables que almacenan los datos de cada elemento
    private int image; // ID del recurso de la imagen (almacenado como entero)
    private String name; // Nombre asociado al elemento

    // Constructor para inicializar los valores de la clase
    public HomeHorModel(int image, String name) {
        this.image = image; // Asigna el ID de la imagen al campo correspondiente
        this.name = name;   // Asigna el nombre al campo correspondiente
    }

    // Getter para obtener el ID de la imagen
    public int getImage() {
        return image;
    }

    // Setter para establecer el ID de la imagen
    public void setImage(int image) {
        this.image = image;
    }

    // Getter para obtener el nombre del elemento
    public String getName() {
        return name;
    }

    // Setter para establecer el nombre del elemento
    public void setName(String name) {
        this.name = name;
    }
}
