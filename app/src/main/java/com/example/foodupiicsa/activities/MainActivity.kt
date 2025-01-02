package com.example.foodupiicsa.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.foodupiicsa.R
import com.example.foodupiicsa.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    // Variables para Firebase y configuración
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar la vista principal
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Firebase
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        // Verificar sesión al inicio
        if (!isLoggedIn()) {
            redirectToLogin()
            return
        }

        // Configuración del Toolbar y Navigation Drawer
        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_home, R.id.nav_comida_diaria, R.id.nav_favoritos, R.id.myCartFragment),
            drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Verificar sesión
        val sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (!isLoggedIn) {
            redirectToLogin()
            return
        }

        // Obtener el usuario actual
        val currentUser = auth.currentUser
        if (currentUser == null) {
            Toast.makeText(this, "No hay usuario actual", Toast.LENGTH_SHORT).show()
            redirectToLogin()
            return
        }

        val btnCerrarSesion = navView.findViewById<Button>(R.id.btnCerrarSesion)
        btnCerrarSesion?.setOnClickListener {
            auth.signOut()
            editor.putBoolean("isLoggedIn", false)
            editor.apply()
            Toast.makeText(this, "Sesión cerrada correctamente", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
            finish()
        }

        // Configurar el encabezado del NavigationView
        val headerView = navView.getHeaderView(0)
        val userNameTextView = headerView.findViewById<TextView>(R.id.etUsuario)
        val emailTextView = headerView.findViewById<TextView>(R.id.etEmail)
        val boletaTextView = headerView.findViewById<TextView>(R.id.etBoleta)

        // Recuperar datos del usuario desde Firebase
        val userId = currentUser.uid
        database.child("users").child(userId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    try {
                        val userName = snapshot.child("username").value?.toString() ?: "Sin nombre"
                        val boleta = snapshot.child("boleta").value?.toString() ?: "Sin boleta"
                        val email = snapshot.child("email").value?.toString() ?: "Sin email"

                        userNameTextView.text = userName
                        boletaTextView.text = boleta
                        emailTextView.text = email
                    } catch (e: Exception) {
                        Toast.makeText(
                            this@MainActivity,
                            "Error al cargar datos: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        this@MainActivity,
                        "Error al cargar datos: ${error.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

    }

    private fun redirectToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun isLoggedIn(): Boolean {
        val currentUser = auth.currentUser
        val sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)
        return currentUser != null && sharedPreferences.getBoolean("isLoggedIn", false)
    }

    override fun onStart() {
        super.onStart()
        if (!isLoggedIn()) {
            redirectToLogin()
        }
    }

    // Agregar esto para manejar el botón de retroceso
    override fun onBackPressed() {
        // No hacer nada o mostrar diálogo de confirmación
        // Esto evita que el usuario pueda salir de la app con el botón de retroceso
    }
}
