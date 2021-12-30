package com.example.projetmobile2021

import androidx.lifecycle.LiveData
import androidx.room.*
import java.time.LocalDate

@Dao
interface MyDAO {

    @Query("SELECT * FROM plante_tab")
    fun getAllPlantes(): List<Plante>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insert(pl : Plante)

    @Query("DELETE FROM plante_tab")
     fun deleteAll()

    @Query("Select * FROM plante_tab where id = :idPlante")
    fun getPlante(idPlante: Int) :List<Plante>

    @Update
    fun updatePlante(vararg plante:Plante)
}