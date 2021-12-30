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
    var dernierArosage: LocalDate,
    var dateFrequenceDebut: List<LocalDate>,
    var dateFrequenceFin: List<LocalDate>,
    var frequence: List<Int>,
    var uriImage : String


)