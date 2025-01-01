package com.example.foodupiicsa.activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.foodupiicsa.R
import com.example.foodupiicsa.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationActivity : AppCompatActivity() {

    //VARIABLES PARA FIREBASEAUTH Y VIEWBINDING
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //INICIAR VIEWBINDING
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //INICIAR FIREBASEAUTH
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        //setContentView(R.layout.activity_registration)
        val etUsername = findViewById<EditText>(R.id.etNombre)
        val etBoleta = findViewById<EditText>(R.id.etBoleta)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        //ajusta los márgenes para evitar superposiciones con las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //CONFIGURAR EL BOTON DE REGISTRO
        binding.btnRegistrar.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val boleta = etBoleta.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            //registerUser()

            if (username.isEmpty() || boleta.isEmpty() || email.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(this,"Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            registerUser(username,boleta,email,password)
            }
        binding.btnLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerUser(username:String, boleta:String,email:String,password:String) {
        val email=binding.etEmail.text.toString().trim()
        val password=binding.etPassword.text.toString().trim()
        val confirmpassword=binding.etPassword2.text.toString().trim()

        //VALIDAR ENTRADAS
        if (email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmail.error = "Correo electrónico no valido"
            binding.etEmail.requestFocus()
            return
        }
        if (password.isEmpty()||password.length<6) {
            binding.etPassword.error = "La contraseña debe almenos tener 6 caracteres"
            binding.etPassword.requestFocus()
            return
        }
        if (password!=confirmpassword){
            binding.etPassword2.error = "Las contraseñas no coinciden"
            binding.etPassword2.requestFocus()
            return
        }

        //REGISTRAR USUARIO CON FIREBASE AUTHENTICATION
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task->
                if(task.isSuccessful){
                    //GUARDAR LOS DATOS ADICIONALES
                    val userId=auth.currentUser?.uid
                    val userMap=mapOf(
                        "username" to username,
                        "boleta" to boleta,
                        "email" to email,
                    )

                    userId?.let {
                        database.child("users").child(it).setValue(userMap)
                            .addOnCompleteListener { saveTask ->
                                if (saveTask.isSuccessful) {
                                    // Mostrar mensaje de éxito y redirigir a MainActivity
                                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this, MainActivity::class.java)
                                    startActivity(intent)
                                    finish() // Cierra la actividad actual para evitar volver atrás
                                } else {
                                    // Manejar error al guardar datos
                                    Toast.makeText(this, "Error al guardar datos: ${saveTask.exception?.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                    }?:run{
                        Toast.makeText(this, "Error al obtener el ID del usuario", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    // Manejar error de registro en Firebase Authentication
                    Toast.makeText(this, "Error de registro: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }

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
}