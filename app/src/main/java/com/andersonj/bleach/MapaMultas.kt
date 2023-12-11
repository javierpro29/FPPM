package com.andersonj.bleach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore

class MapaMultas : AppCompatActivity(), OnMapReadyCallback {

    val db = FirebaseFirestore.getInstance()

    private lateinit var map:GoogleMap

    private lateinit var multaList: ArrayList<Multa>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa_multas)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        createFragment()
    }

    private fun createFragment(){
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(/* callback = */ this)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun mostrarMarcadorEnMapa(latitud: Double?, longitud: Double?, cedula:String?, placa:String?, motivo:String?) {

        if (latitud != null && longitud != null) {
            val ubicacion = LatLng(latitud, longitud)

            map.addMarker(MarkerOptions().position(ubicacion)
                .title("Cedula: $cedula")
                .snippet("""Placa: $placa \
                    "Motivo: $motivo""")

            )
        } else {

        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val docRef = db.collection("multas")
            .get()
            .addOnSuccessListener {result ->
                for(document in result){
                    //Log.d("TAG"," ${document.id} => ${document.data} ")

                    val cedula = document.getString("cedula")
                    val placa = document.getString("placa")
                    val motivo = document.getString("motivo")
                    val latitud = document.getString("latitud")
                    val longitud = document.getString("longitud")

                    val lat: Double? = latitud?.toDoubleOrNull()
                    val lon: Double? = longitud?.toDoubleOrNull()

                    mostrarMarcadorEnMapa(lat, lon, cedula, placa, motivo)


//                    val multa = Multa(
//                        "Cédula: $cedula",
//                        "Placa: $placa",
//                        "Latitud: $latitud",
//                        "Longitud: $longitud",
//                    )

//                   multaList.add(multa)

                }

            }
            .addOnFailureListener{exception ->
                Log.d("TAG", "Error al obtener los documentos $exception")
            }

        val coordinates = LatLng(18.4800379, -69.9881653)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, 10f),
            4000,
            null
        )
    }
}