package com.example.alarma.Alarmas

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.textclassifier.ConversationActions
import androidx.core.app.NotificationCompat
import com.example.alarma.MainActivity
import com.example.alarma.R
import kotlinx.android.synthetic.main.time_picker.view.*

class NotificacionPush {

    val notificacion = "peticion nueva"
    val notifocation_chanel_id = "miChannelId"


    fun notificacion(context: Context, mensaje: String, numero: Int) {
        val intent = Intent(context, MainActivity::class.java)
        val gestorNotificacion =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notchannel = NotificationChannel(
                notifocation_chanel_id,
                "Mi notificacion",
                NotificationManager.IMPORTANCE_HIGH
            )
            notchannel.description = "Descripcion"
            notchannel.lightColor = Color.BLUE
            notchannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notchannel.enableVibration(true)
            gestorNotificacion.createNotificationChannel(notchannel)
        }

        val builder = NotificationCompat.Builder(context, notifocation_chanel_id)
            .setContentTitle("AlarmaApp")
            .setContentText(mensaje)
            .setColorized(true)
            .setColor(Color.BLUE)
            .setNumber(numero)
            .setSmallIcon(R.drawable.ic_access_alarm_black_24dp)
            .setContentIntent(
                PendingIntent.getActivity(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            )
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT in 19..25) {
            gestorNotificacion.notify(notificacion, 0, builder.build())
        } else {
            gestorNotificacion.notify(notificacion.hashCode(), builder.build())
        }
    }
}
