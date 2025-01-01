package com.example.foodupiicsa.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
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

    //VARIABLES DATABASE
    private lateinit var auth:FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //INCIALIZAR FIREBASE
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        //CONFIGURACIÓN DE TOOLBAR Y NAVEGATION DRAWER
        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_comida_diaria, R.id.nav_favoritos, R.id.myCartFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)
        
        //Inflar el encabezado del NavigationView
        val headerView = navView.getHeaderView(0)//Indice 0 corresponde al encabezado
        val userNameTextView=headerView.requireViewById<TextView>(R.id.etUsuario)
        val emailTextView=headerView.findViewById<TextView>(R.id.etEmail)
        val boletaTextView=headerView.findViewById<TextView>(R.id.etBoleta)
        //OBTENER AL USUARIO ACTUAL
        val currentUser = auth.currentUser
        if(currentUser==null)
        {
            Toast.makeText(this,"No hay usuario actual", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            return
        }
        
        val userId=currentUser.uid
        database.child("users").child(userId).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val userName = snapshot.child("username").value?.toString() ?: "Sin nombre"
                    val boleta = snapshot.child("boleta").value?.toString() ?: "Sin boleta"
                    val email = snapshot.child("email").value?.toString() ?: "Sin email"

                    userNameTextView.text = userName
                    boletaTextView.text = boleta
                    emailTextView.text = email
                } catch (e:Exception) {
                    Toast.makeText(
                        this@MainActivity,
                        "Error al cargar datos: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
                override fun onCancelled(error: DatabaseError) {
            Toast.makeText(this@MainActivity, "Error al cargar datos: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}