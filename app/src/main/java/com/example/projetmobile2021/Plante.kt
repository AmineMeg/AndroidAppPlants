package com.example.projetmobile2021

import androidx.room.*

@Entity(tableName = "plante_tab")
data class Plante (


    @PrimaryKey(autoGenerate=true) var id : Int,
    var nom : String,
    var nomLatin : String,


)