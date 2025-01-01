package com.example.foodupiicsa.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.foodupiicsa.R
import com.example.foodupiicsa.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    //VARIABLES PARA FIREBASE:
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //  INICIALIZAR VIEWBINDING
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //INICIALIZAR FIREBASE
        auth = FirebaseAuth.getInstance()

        //AJUSTAR INSETS PARA EL DISEÑO DE LA PANTALLA
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //CONFIGRURAR EL BOTON DE INICIO DE SESIÓN
        binding.btnLogin.setOnClickListener{
            loginUser()
        }

        //REDIRIGIR AL REGISTRO SI EL USUARIO NO TINENE CONTRASEÑA
        binding.btnRegistrar.setOnClickListener{
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

    }

    //FUNCION PARA INICIAR SESIÓN
    private fun loginUser(){
        val email = binding.etEmail.text.toString().trim()
        val password=binding.etPassword.text.toString().trim()

        //VALIDAR ENTRADAS
        if (email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmail.error = "Correo electrónico no valido"
            binding.etEmail.requestFocus()
            return
        }
        if (password.isEmpty()||password.length<6) {
            binding.etPassword.error = "La contraseña debe almenos tener 3 caracteres"
            binding.etPassword.requestFocus()
            return
        }

        //VERIFICAR SI ES ADMINISTRADOR
        if (email=="admin@email.com"&& password=="admin123"){
            Toast.makeText(this, "Inicio de sesión exitoso como administrador, exitoso", Toast.LENGTH_SHORT).show()
            Log.d("LoginActivity", "Inicio de sesión exitoso como administrador")
            val adminIntent = Intent(this, AdminOrdersActivity::class.java)
            adminIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(adminIntent)
            return
        }

        //INICIAR SESIÓN CON FIREBASE
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Redirigir al MainActivity si el inicio de sesión es exitoso
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    // Mostrar error si no se puede iniciar sesión
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

    }

    //función admin



    fun registrer(view: View) {
        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navigateToWelcome()
    }

    private fun navigateToWelcome() {
        val intent = Intent(this, WelcomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
        finish()
    }

    fun mainActivity(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent) // Inicia la nueva actividad
    }
}