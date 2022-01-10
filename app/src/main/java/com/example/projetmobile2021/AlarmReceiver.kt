package com.example.projetmobile2021

import android.app.PendingIntent
import android.content.Intent
import android.content.Context
import android.content.BroadcastReceiver
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import java.time.LocalDate

class AlarmReceiver: BroadcastReceiver() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        var plantes = listOf<Plante>()
        Log.i("tesst", " Je suis dans le thread ")
        var check: Boolean = false;
        Thread{
            Log.i("tesst", " OLOLOLOLOLOO ")
            plantes = PlanteBD.getDatabase(context!!).MyDAO().getAllPlantes()
            Log.i("tesst", plantes.size.toString())
            for (i in 0..plantes.size-1) {
                if (plantes[i].prochainArosage.equals(LocalDate.now()))
                    check = true;
            }
            if (check == true){
                val i = Intent(context, ArrosageActivity::class.java)
                intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                val pendingIntent = PendingIntent.getActivity(context, 0, i, 0)
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
        }.start()
    }
}