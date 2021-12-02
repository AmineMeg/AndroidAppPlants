package com.example.projetmobile2021

import androidx.room.*

@Entity
data class Plante (

    @PrimaryKey
    var id : Int,
    var nom : String,
    var nomLatin : String,


)