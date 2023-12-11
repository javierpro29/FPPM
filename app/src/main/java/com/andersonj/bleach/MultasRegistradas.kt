package com.andersonj.bleach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class MultasRegistradas : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

    private lateinit var multaArrayList: ArrayList<Multa>
    private lateinit var multaRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multas_registradas)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        multaRecyclerView = findViewById(R.id.multaList)
        multaRecyclerView.layoutManager = LinearLayoutManager(this)
        multaRecyclerView.setHasFixedSize(true)

        multaArrayList = arrayListOf<Multa>()
        getMultaData()

    }

    private fun getMultaData(){
        val docRef = db.collection("multas")
            .get()
            .addOnSuccessListener {result ->
                for(document in result){
                    //Log.d("TAG"," ${document.id} => ${document.data} ")

                    val cedula = document.getString("cedula")
                    val placa = document.getString("placa")
                    val motivo = document.getString("motivo")
                    val comentario = document.getString("comentario")
                    val latitud = document.getString("latitud")
                    val longitud = document.getString("longitud")
                    val fecha = document.getString("fecha")
                    val hora = document.getString("hora")
                    val foto = document.getString("foto")

                    val multa = Multa(
                        "Cédula: $cedula",
                        "Placa: $placa",
                        "Motivo: $motivo",
                        "Comentario: $comentario",
                        "Latitud: $latitud",
                        "Longitud: $longitud",
                        "Fecha: $fecha",
                        "hora: $hora",
                        "$foto"
                        )
                    multaArrayList.add(multa)

                }
                multaRecyclerView.adapter = MultaAdapter(multaArrayList)
            }
            .addOnFailureListener{exception ->
                Log.d("TAG", "Error al obtener los documentos $exception")
            }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}