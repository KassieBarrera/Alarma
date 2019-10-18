package com.example.alarma

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.provider.AlarmClock
import java.util.*

class GuardarDatos(contect: Context) {

    val context: Context? = contect
    var sharedPreferences: SharedPreferences? = context!!.getSharedPreferences("shared", Context.MODE_PRIVATE)

    fun sharedPreferences(hora: Int, minutos: Int){
        val editar = sharedPreferences!!.edit()
        editar.putInt("hora", hora)
        editar.putInt("minuto", minutos)
        editar.apply()
    }

    fun obtenerHora():Int{
        return sharedPreferences!!.getInt("hora", 0)
    }

    fun ontenerMinutos():Int{

        return sharedPreferences!!.getInt("minuto", 0)
    }

    fun programarAlarma() {
        val hora: Int = obtenerHora()
        val minutos: Int = ontenerMinutos()
        val calendario = Calendar.getInstance()

        calendario.set(Calendar.HOUR_OF_DAY, hora)
        calendario.set(Calendar.MINUTE, minutos)
        calendario.set(Calendar.SECOND, 0)

        val getorAlarma = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, MyBroadcastReciver::class.java)

        intent.putExtra("mensaje", "Alarma fijada a las" + obtenerHora() + ":" + ontenerMinutos())
        intent.action = "com.gestoralarma"

        val iPending = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        getorAlarma.setRepeating(AlarmManager.RTC_WAKEUP, calendario.timeInMillis, AlarmManager.INTERVAL_DAY, iPending)
    }
}