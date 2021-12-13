package com.example.projetmobile2021

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.projetmobile2021.databinding.ActivityAjoutPlanteBinding
import java.util.*

class AjoutPlanteActivity : AppCompatActivity() {

    val model by lazy { ViewModelProvider(this).get(PlanteViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajout_plante)

    }

    fun ajoutPlante(view: android.view.View) {

        var nom : EditText = findViewById(R.id.nom)
        var nomL : EditText = findViewById(R.id.nomLatin)
        var dateFreq1 : EditText = findViewById(R.id.editTextDate)
        var dateFreq2 : EditText = findViewById(R.id.editTextDate2)
        var listDateSep : List<String> = dateFreq1.text.toString().split("/").map { it }
        var listDateSep2 : List<String> =  dateFreq2.text.toString().split("/").map { it }
        var freq1 : EditText = findViewById(R.id.editTextNumber)
        //var listDate : List<Date> = arrayListOf(Date(listDateSep[0].toInt(),listDateSep[1].toInt(),listDateSep[2].toInt()),Date(listDateSep2[0].toInt(),listDateSep2[1].toInt(),listDateSep2[2].toInt()))
        model.addPlante(nom.text.toString(),nomL.text.toString(), arrayListOf(freq1.text.toString().toInt()))

    }


}