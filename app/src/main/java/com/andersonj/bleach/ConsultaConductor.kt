package com.andersonj.bleach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class ConsultaConductor : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

    lateinit var Serch:EditText
    lateinit var btnConsultar:Button

    lateinit var Title:TextView
    lateinit var Foto:ImageView
    lateinit var Licencia:TextView
    lateinit var Nombre:TextView
    lateinit var Apellido:TextView
    lateinit var Fecha:TextView
    lateinit var Direccion:TextView
    lateinit var Telefono:TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_conductor)

        Serch = findViewById<EditText>(R.id.Serch)
        btnConsultar = findViewById<Button>(R.id.btnConsultar)

        Title = findViewById<EditText>(R.id.ConductorTitle)
        Foto = findViewById<ImageView>(R.id.foto)
        Licencia = findViewById<TextView>(R.id.licencia)
        Nombre = findViewById<TextView>(R.id.nombre)
        Apellido = findViewById<TextView>(R.id.apellido)
        Fecha = findViewById<TextView>(R.id.fecha)
        Direccion = findViewById<TextView>(R.id.direccion)
        Telefono = findViewById<TextView>(R.id.telefono)

        leer()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun leer(){
        btnConsultar.setOnClickListener(){
            val lic = Serch.text.toString()

            limpiarText()
            Title.text = "No se ha encontrado conductor con el No. de licencia ingresado"

            val docRef = db.collection("conductores")
                .whereEqualTo("licencia", "$lic")
                .get()
                .addOnSuccessListener { result ->
                    for(document in result){

                        val licencia = document.getString("licencia")
                        val foto = document.getString("foto")
                        val nombre = document.getString("nombre")
                        val apellido = document.getString("apellido")
                        val fecha = document.getString("fechaNacimiento")
                        val direccion = document.getString("direccion")
                        val telefono = document.getString("telefono")

                        Title.text = "Información del Conductor"
                        Licencia.text = "Licencia: $licencia"
                        Nombre.text = "Nombre: $nombre"
                        Apellido.text = "Apellido: $apellido"
                        Fecha.text = "Fecha: $fecha"
                        Direccion.text = "Dirección: $direccion"
                        Telefono.text = "Telefono: $telefono"


                        Picasso.get().load(foto).into(Foto)

                    }
                }
                .addOnFailureListener{ exception ->
                    Log.d("TAG", """ Error al obtener los documentos $exception """)
                }
        }
    }

    private fun limpiarText(){
        Title.text = ""
        Licencia.text = ""
        Nombre.text = ""
        Apellido.text = ""
        Fecha.text = ""
        Direccion.text = ""
        Telefono.text = ""
        Foto.setImageDrawable(null)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}