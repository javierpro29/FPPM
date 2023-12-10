package com.andersonj.bleach

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {

    lateinit var btnLogin:Button
    lateinit var btnRegistrar:Button
    lateinit var txCorreo:EditText
    lateinit var txPassword:EditText

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

         btnLogin= findViewById<Button>(R.id.btnLogin)
         btnRegistrar = findViewById<Button>(R.id.btnRegistro)
         txCorreo = findViewById<EditText>(R.id.editTextEmail)
         txPassword = findViewById<EditText>(R.id.editTextPassword)

        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener(){
            val email = txCorreo.text.toString().trim()
            val password = txPassword.text.toString().trim()

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Inicio de sesión exitoso
                            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                            // Puedes redirigir a otra actividad aquí
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // Si el inicio de sesión falla, muestra un mensaje al usuario.
                            Toast.makeText(this, "Inicio de sesión fallido", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        btnRegistrar.setOnClickListener {
            val email = txCorreo.text.toString().trim()
            val password = txPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Verificar si el usuario ya está registrado
            auth.fetchSignInMethodsForEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val signInMethods = task.result?.signInMethods
                    if (signInMethods != null && signInMethods.isNotEmpty()) {
                        // El usuario ya está registrado
                        Toast.makeText(this, "Este usuario ya está registrado", Toast.LENGTH_SHORT).show()
                    } else {
                        // El usuario no está registrado, proceder con el registro
                        // Crear un nuevo usuario con Firebase
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this) { registrationTask ->
                                if (registrationTask.isSuccessful) {
                                    // Registro exitoso
                                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                                } else {
                                    // Si el registro falla, muestra un mensaje al usuario.
                                    Toast.makeText(
                                        this,
                                        "Error en el registro: ${registrationTask.exception?.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    }
                } else {
                    // Error al verificar la existencia del usuario
                    Toast.makeText(this, "Error al verificar la existencia del usuario", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }


}
