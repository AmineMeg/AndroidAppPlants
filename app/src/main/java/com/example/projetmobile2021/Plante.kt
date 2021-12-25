package com.example.projetmobile2021

import androidx.room.*
import java.time.LocalDate
import java.time.Month
import java.util.*

@Entity(tableName = "plante_tab")
data class Plante(

    @PrimaryKey(autoGenerate=true) var id: Int,
    var nom: String,
    var nomLatin: String?,
    var dernierArosage: LocalDate?,
    var dateFrequence: List<LocalDate>,
    var frequence: List<Int>


)