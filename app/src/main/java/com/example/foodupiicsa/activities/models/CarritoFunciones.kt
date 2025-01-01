package com.example.foodupiicsa.activities.models

import android.util.Log

class CarritoFunciones private constructor() {
    private val cartItems = mutableListOf<CartModel>()

    // Añadir elementos al carrito
    fun addItem(item: CartModel) {
        cartItems.add(item)
    }

    // Obtener los elementos del carrito
    fun getCartItems(): List<CartModel> {
        return cartItems
    }

    // Limpiar el carrito
    fun clearCart() {
        cartItems.clear()
    }

    companion object {
        // Instancia única del Singleton
        private var instance: CarritoFunciones? = null

        @JvmStatic
        fun getInstance(): CarritoFunciones {
            if (instance == null) {
                instance = CarritoFunciones()
            }
            return instance!!
        }
    }
}
