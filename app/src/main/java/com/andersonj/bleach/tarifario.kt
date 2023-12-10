package com.andersonj.bleach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class tarifario : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    //var tarifaList = listOf<Tarifa>()
    private lateinit var tarifaArrayList : ArrayList<Tarifa>
    private lateinit var tarifaRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarifario)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tarifaRecyclerView = findViewById(R.id.tarifarioList)
        tarifaRecyclerView.layoutManager = LinearLayoutManager(this)
        tarifaRecyclerView.setHasFixedSize(true)

        tarifaArrayList = arrayListOf<Tarifa>()
        getTarifaData()

    }

    private fun getTarifaData(){
//        val tarifa = Tarifa("Hola Mundo", "Ley 1", "1000")
//        tarifaArrayList.add(tarifa)
//        tarifaRecyclerView.adapter = TarifarioAdapter(tarifaArrayList)

        val docRef = db.collection("tarifario")
            .get()
            .addOnSuccessListener {result ->
                for(document in result){
                    //Log.d("TAG"," ${document.id} => ${document.data} ")

                    val nombre = document.getString("nombre")
                    val ley = document.getString("ley")
                    val monto = document.getString("monto")

                    val tarifa = Tarifa("$nombre", "$ley", "Monto: $monto")
                    tarifaArrayList.add(tarifa)

                }
                tarifaRecyclerView.adapter = TarifarioAdapter(tarifaArrayList)
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