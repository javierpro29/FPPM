package com.andersonj.bleach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class AplicarMulta : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

    lateinit var btnAplicar:Button

    lateinit var txCedula:EditText
    lateinit var txPlaca:EditText
    lateinit var txMotivo:EditText
    lateinit var txFoto:EditText
    lateinit var txComentario:EditText
    lateinit var txLatitud:EditText
    lateinit var txLongitud:EditText
    lateinit var txFecha:EditText
    lateinit var txHora:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aplicar_multa)

        btnAplicar = findViewById<Button>(R.id.aplicar)

        txCedula = findViewById<EditText>(R.id.txCedula)
        txPlaca = findViewById<EditText>(R.id.txPlaca)
        txMotivo = findViewById<EditText>(R.id.txMotivo)
        txFoto = findViewById<EditText>(R.id.txFoto)
        txComentario = findViewById<EditText>(R.id.txComentario)
        txLatitud = findViewById<EditText>(R.id.txLatitud)
        txLongitud = findViewById<EditText>(R.id.txLongitud)
        txFecha = findViewById<EditText>(R.id.txFecha)
        txHora = findViewById<EditText>(R.id.txHora)

        escribir()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun escribir(){

        btnAplicar.setOnClickListener(){

            val data = hashMapOf(
                "cedula" to "${txCedula.text}",
                "placa" to "${txPlaca.text}",
                "motivo" to "${txMotivo.text}",
                "foto" to "${txFoto.text}",
                "comentario" to "${txComentario.text}",
                "latitud" to "${txLatitud.text}",
                "longitud" to "${txLongitud.text}",
                "fecha" to "${txFecha.text}",
                "hora" to "${txHora.text}",
            )


            if (txCedula.text.isNotEmpty()
                && txPlaca.text.isNotEmpty()
                && txMotivo.text.isNotEmpty()
                && txFoto.text.isNotEmpty()
                && txComentario.text.isNotEmpty()
                && txLatitud.text.isNotEmpty()
                && txLongitud.text.isNotEmpty()
                && txFecha.text.isNotEmpty()
                && txHora.text.isNotEmpty()
                ) {

                db.collection("multas")
                    .add(data)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Registro agregado correctamente", Toast.LENGTH_SHORT).show()
                        limpiar()
                    }
                    .addOnFailureListener{e ->
                        Toast.makeText(this, "El registro no se ha agregado, revise los datos", Toast.LENGTH_SHORT).show()
                    }


            }else{
                Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show()

            }






        }

    }

    private fun limpiar(){
        txCedula.setText("")
        txPlaca.setText("")
        txMotivo.setText("")
        txFoto.setText("")
        txComentario.setText("")
        txLatitud.setText("")
        txLongitud.setText("")
        txFecha.setText("")
        txHora.setText("")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
