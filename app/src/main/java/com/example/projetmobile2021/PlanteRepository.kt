package com.example.projetmobile2021

import androidx.annotation.WorkerThread

class PlanteRepository(private val myDAO : MyDAO) {
    val allPlantes : List<Plante> = myDAO.getAllPlantes()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(plante : Plante){
        myDAO.insert(plante)
    }

}