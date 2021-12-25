package com.example.projetmobile2021

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Month
import java.util.*

class PlanteViewModel(application: Application) : AndroidViewModel(application) {

    val dao = PlanteBD.getDatabase(application).MyDAO()
    val TAG = "AddPaysViewModel"

    @RequiresApi(Build.VERSION_CODES.O)
    fun addPlante(){
        Thread{
            dao.insert(
                Plante(
                    id = 0, nom = "tesst", nomLatin = "test",
                    dateFrequence = arrayListOf(LocalDate.of(2021,1,15),LocalDate.of(2021,4,15)),
                    dernierArosage =  LocalDate.of(2021,11,28),
                    frequence = arrayListOf(2)
                )
            )
            val b = dao.getAllPlantes()
            for (au in b) {
                Log.d("test","nom = ${au.nom}")
            }
        }.start()

    }

    fun getAllPlante() {
    }

}