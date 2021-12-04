package com.example.projetmobile2021

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PlanteViewModel(application: Application) : AndroidViewModel(application) {

    val dao = PlanteBD.getDatabase(application).MyDAO()
    //val listPlantes : List<Plante> = dao.getAllPlantes()
    val TAG = "AddPaysViewModel"

    fun addPlante(nom : String, nomLatin : String, ){
        Thread{
            dao.insert(
                Plante(
                    id = 0, nom = nom.trim(),nomLatin = nomLatin.trim()
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