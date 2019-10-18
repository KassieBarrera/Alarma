package com.example.alarma

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gDatos = GuardarDatos(applicationContext)
        txtHora.text = gDatos.obtenerHora().toString() + ":" + gDatos.ontenerMinutos().toString()

        btnOk.setOnClickListener {
            val tiempo = Time_picker()
            val fragmentManager = supportFragmentManager

            tiempo.show(fragmentManager, "Selecciona una hora")
        }

    }

    fun setearHora(hora: Int, minutos: Int){
        txtHora.text =  hora.toString() + ":" + minutos.toString()

        val guardarDatos = GuardarDatos(applicationContext)
        guardarDatos.programarAlarma()
        guardarDatos.sharedPreferences(hora, minutos)
    }
}
