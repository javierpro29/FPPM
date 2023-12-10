package com.andersonj.bleach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class ConsultaVehiculo : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    lateinit var btnConsultar:Button
    lateinit var SerchPlaca:EditText

    lateinit var txVehiculoTitle:TextView
    lateinit var txPlaca:TextView
    lateinit var txMarca:TextView
    lateinit var txModelo:TextView
    lateinit var txColor:TextView
    lateinit var txAno:TextView
    lateinit var txTipo:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_vehiculo)

        SerchPlaca = findViewById<EditText>(R.id.txPlaca)
        btnConsultar = findViewById<Button>(R.id.btnConsultar)

        txVehiculoTitle = findViewById<TextView>(R.id.vehiculoTitle)
        txPlaca = findViewById<TextView>(R.id.placa)
        txMarca = findViewById<TextView>(R.id.marca)
        txModelo = findViewById<TextView>(R.id.modelo)
        txColor = findViewById<TextView>(R.id.color)
        txAno = findViewById<TextView>(R.id.ano)
        txTipo = findViewById<TextView>(R.id.tipo)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        leer()
    }

    private fun leer() {
        btnConsultar.setOnClickListener() {
            val placa = SerchPlaca.text.toString()

            limpiarText()
            txVehiculoTitle.text = "No se ha encontrado el vehiculo"

            val docRef = db.collection("vehiculos")
                .whereEqualTo("placa", "$placa")
                .get()
                .addOnSuccessListener { result ->
                    for(document in result){

                        val placa = document.getString("placa")
                        val marca = document.getString("marca")
                        val modelo = document.getString("modelo")
                        val color = document.getString("color")
                        val ano = document.getString("año")
                        val tipo = document.getString("tipo")

                        txVehiculoTitle.text = "Información del vehiculo"
                        txPlaca.text = "Placa: $placa"
                        txMarca.text = "Marca: $marca"
                        txModelo.text = "Modelo: $modelo"
                        txColor.text = "Color: $color"
                        txAno.text = "Año: $ano"
                        txTipo.text = "Tipo: $tipo"
                    }
                }
                .addOnFailureListener{ exception ->
                    Log.d("TAG", """ Error al obtener los documentos $exception """)
                }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun limpiarText(){
        txVehiculoTitle.text = ""
        txPlaca.text = ""
        txMarca.text = ""
        txModelo.text = ""
        txColor.text = ""
        txAno.text = ""
        txTipo.text = ""
    }
}