package com.example.alarma

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.alarma.Alarmas.NotificacionPush

class MyBroadcastReciver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action == ("com.gestoralarma")){
            val mensaje = intent.extras
            //Toast.makeText(context, mensaje.getString("mensaje"), Toast.LENGTH_SHORT).show()
            val notificacion = NotificacionPush()
            notificacion.notificacion(context!!, mensaje!!.getString("mensaje")!!, 1)
    } else if(intent.action == ("android.permission.RECEIVE_BOOT_COMPLETED")){
            val gDatos = GuardarDatos(context!!)
            gDatos.programarAlarma()
        }
    }
}