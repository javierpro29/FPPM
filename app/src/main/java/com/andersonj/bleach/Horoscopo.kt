package com.andersonj.bleach

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Horoscopo : AppCompatActivity() {

    private lateinit var spinnerSigno: Spinner
    private lateinit var tvCaracteristicas: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horoscopo)

        // Inicializar vistas
        spinnerSigno = findViewById(R.id.spinnerSigno)
        tvCaracteristicas = findViewById(R.id.tvCaracteristicas)

        // Lista de signos del zodiaco
        val signos = arrayOf("Aries", "Tauro", "Géminis", "Cáncer", "Leo", "Virgo", "Libra", "Escorpio", "Sagitario", "Capricornio", "Acuario", "Piscis")

        // Adaptador para el Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, signos)
        spinnerSigno.adapter = adapter

        // Manejar la selección del Spinner
        spinnerSigno.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                // Mostrar características asociadas al signo seleccionado
                val signoSeleccionado = signos[position]
                mostrarCaracteristicas(signoSeleccionado)
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {

            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    // Método para mostrar las características asociadas al signo seleccionado
    private fun mostrarCaracteristicas(signo: String) {

        val caracteristicas = obtenerCaracteristicas(signo)
        tvCaracteristicas.text = caracteristicas
    }

    // Método de ejemplo para obtener características según el signo
    private fun obtenerCaracteristicas(signo: String): String {

        return when (signo) {
            "Aries" -> "Trabajo y negocios: alguien influyente elogiará su tarea y le conducirá al éxito en su proyecto. Amor: su búsqueda de emociones creará roces en la pareja pero serán un estímulo."
            "Tauro" -> "Trabajo y negocios: momento de singulares logros y nuevos planes; propicio nueva actividad o negocio. Amor: buscará el regalo ideal para esa persona que le hace feliz día tras día."
            "Géminis" -> "Trabajo y negocios: si se empecina en sostener un proyecto con fallas, algunos colegas se alejarán. Amor: su brillante creatividad encantará a toda persona que se acerque; romance. "
            "Cáncer" -> "Trabajo y negocios: momento poco propicio para acciones audaces ni para tomar riesgos. Cautela. Amor: su actitud comprensiva y amable atraerá a alguien interesante para conocer. "
            "Leo" -> "Trabajo y negocio: para estar alerta y desconfiar de halagos y gente maliciosa. Necesidad de aliados. Amor: un nuevo romance podría hacer que gaste de más, si lo controla, todo irá mejor. "
            "Virgo" -> "Trabajo y negocios: insistirá en avanzar a pesar de los errores; pronto, otros le imitarán. Amor: si concentra su atención en la belleza de su pareja, verá un mundo resplandeciente. "
            "Libra" -> "Trabajo y negocios: los desacuerdos bloquearán la senda del éxito pero se resolverán. Amor: una cita le alterará los nervios pero anticipará un romance inesperado. "
            "Escorpio" -> "Trabajo y negocios: sorteará una situación difícil y logrará que admiren su coraje. Amor: los gestos importarán más que las palabras y así la relación superará conflictos. "
            "Sagitario" -> "Trabajo y negocios: repentino cambio de rumbo. Sus habilidades estarán a pleno. Amor: su optimismo creará lazos que enriquecerán momentos cálidos en la pareja. "
            "Capricornio" -> "Trabajo y negocios: profusión de errores generarán desaliento pero lo revertirá. Amor: se alejará de las emociones fuertes y disfrutará del sosiego y la paz de la relación. "
            "Acuario" -> "Trabajo y negocios: alguien poco idóneo le complicará porque afecta su proyecto; nuevo plan. Amor: en la relación se dejará de lado las quejas y los momentos gratos se prolongarán. "
            "Piscis" -> "Trabajo y negocios: con fuerte percepción verá el interior de nuevos colegas y sabrá qué hacer. Amor: hallará el lugar que le brinda la paz íntima que necesita para madurar la relación. "
            else -> ""
        }
    }
}
