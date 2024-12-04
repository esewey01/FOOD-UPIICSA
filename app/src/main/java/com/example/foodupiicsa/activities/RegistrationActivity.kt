package com.example.foodupiicsa.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.foodupiicsa.MainActivity
import com.example.foodupiicsa.R

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_registration)

        //ajusta los márgenes para evitar superposiciones con las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    //Funcion para iniciar Login
    fun login(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent) // Inicia la nueva actividad
    }
    override fun onBackPressed() {
        // Redirige a WelcomeActivity al presionar atrás
        val intent = Intent(this, WelcomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    fun mainActivity(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent) // Inicia la nueva actividad
    }
}