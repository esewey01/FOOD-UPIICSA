package com.example.foodupiicsa;

import android.app.Application;
import com.google.firebase.FirebaseApp;

public class FOODUPIICSA extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Inicializa Firebase
        FirebaseApp.initializeApp(this);
    }
}
