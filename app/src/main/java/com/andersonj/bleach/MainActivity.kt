package com.andersonj.bleach

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var drawerLayout: DrawerLayout
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        drawerLayout = findViewById(R.id.drawer_layout)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PortadaFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_portada)
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_portada -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PortadaFragment()).commit()

            R.id.nav_tarifario -> getTarifario()

            R.id.nav_consulta_vehiculo -> getConsultarVehiculo()

            R.id.nav_consulta_conductor -> getConsultarConductor()

            R.id.nav_noticias -> getNoticias()

            R.id.nav_aplicar_multa -> getAplicarMulta()

            R.id.nav_multas_registradas -> getMultasRegistradas()

            R.id.nav_mapa_multas -> getMapaMultas()

            R.id.nav_clima -> getClima()

            R.id.nav_horoscopo -> getHoroscopo()


            R.id.nav_cerrarSesion -> cerrarSesion()

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onBackPressed() {
        super.onBackPressed()
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer((GravityCompat.START))
        }else{
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun getTarifario(){
        val intent = Intent(this, tarifario::class.java)
        startActivity(intent)
    }

    private fun getConsultarVehiculo(){
        val intent = Intent(this, ConsultaVehiculo::class.java)
        startActivity(intent)
    }

    private fun getConsultarConductor(){
        val intent = Intent(this, ConsultaConductor::class.java)
        startActivity(intent)
    }

    private fun getNoticias() {
        val intent = Intent(this, Noticias::class.java)
        startActivity(intent)
    }
      
    private fun getAplicarMulta(){
        val intent = Intent(this, AplicarMulta::class.java)
        startActivity(intent)
    }

    private fun getMultasRegistradas(){
        val intent = Intent(this, MultasRegistradas::class.java)
        startActivity(intent)
    }

    private fun getMapaMultas(){
        val intent = Intent(this, MapaMultas::class.java)
        startActivity(intent)
    }

    private fun getClima(){
        val intent = Intent(this, Clima::class.java)
        startActivity(intent)
    }


    private fun getHoroscopo(){
        val intent = Intent(this, Horoscopo::class.java)

        startActivity(intent)
    }



    private fun cerrarSesion(){
        auth.signOut()

        // Redirigir al usuario a la pantalla de inicio de sesión
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }


}