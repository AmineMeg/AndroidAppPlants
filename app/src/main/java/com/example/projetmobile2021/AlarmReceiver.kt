package com.example.projetmobile2021

import android.app.PendingIntent
import android.content.Intent
import android.content.Context
import android.content.BroadcastReceiver
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val i = Intent(context, ArrosageActivity::class.java)
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context,0, i, 0)
        val builder = NotificationCompat.Builder(context!!, "foxandroid")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Arroser les plantes !!")
            .setContentText("Il est l'heure d'arroser vos plantes")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(124, builder.build())
    }
}