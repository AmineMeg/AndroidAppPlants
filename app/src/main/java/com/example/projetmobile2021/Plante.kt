package com.example.projetmobile2021

import androidx.room.*
import java.util.*

@Entity(tableName = "plante_tab")
data class Plante (

    @PrimaryKey(autoGenerate=true) var id : Int,
    var nom : String,
    var nomLatin : String?,
    var dernierArosage : Date?,
    var dateFrequence : List<Date>?,
    var frequence : List<Int>?



)