package com.example.projetmobile2021

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.ContentView
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate

class ArrosageActivity : AppCompatActivity() {

    val model by lazy { ViewModelProvider(this).get(PlanteViewModel::class.java)}
    var plantes = MutableLiveData<List<Plante>>()
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arrosage)

        val recyclerView = findViewById(R.id.recycler) as RecyclerView
        val adapter = ArrosageAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        model.getPlante()

        model.plantes.observe(this){
            adapter.setPlante(it)

        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateDatePlante(plante:Plante){
        plante.dernierArosage = LocalDate.now()
        model.updatePlante(plante)
    }
}