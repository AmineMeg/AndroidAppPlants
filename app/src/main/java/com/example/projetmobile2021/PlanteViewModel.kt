package com.example.projetmobile2021

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.*

class PlanteViewModel(application: Application) : AndroidViewModel(application) {

    val dao = PlanteBD.getDatabase(application).MyDAO()
    //val listPlantes : List<Plante> = dao.getAllPlantes()
    val TAG = "AddPaysViewModel"

    fun addPlante(nom : String, nomLatin : String,freq : List<Int>){
        Thread{
            dao.insert(
                Plante(
                    id = 0, nom = nom.trim(),nomLatin = nomLatin.trim(),
                    dateFrequence = arrayListOf(Date(2021,11,12)),
                    dernierArosage = Date(2021,11,12),
                    frequence = freq
                )
            )
        }.start()

    }

    fun getPlantes(): List<Plante>{
        var listePlante : List<Plante> = listOf()
        Thread{
            listePlante = dao.getAllPlantes()

        }.start()
        return listePlante

    }
}