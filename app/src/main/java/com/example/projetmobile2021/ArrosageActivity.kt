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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate

class ArrosageActivity : AppCompatActivity() {

    val model by lazy { ViewModelProvider(this).get(PlanteViewModel::class.java)}

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arrosage)

        val recyclerView = findViewById(R.id.recycler) as RecyclerView
        val adapter = ArrosageAdapter(this)
        var listePlante : MutableList<Plante> = mutableListOf()
        val bd = PlanteBD.getDatabase(application)
        val t= Thread{listePlante=bd.MyDAO().getAllPlantes().toMutableList()}
        t.start()
        t.join()
        Log.d("test45",listePlante.toString())
        adapter.setPlante(listePlante)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateDatePlante(plante:Plante){
        plante.dernierArosage = LocalDate.now()
        model.updatePlante(plante)
    }
}