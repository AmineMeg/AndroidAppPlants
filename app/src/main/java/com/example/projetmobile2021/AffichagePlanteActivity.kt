package com.example.projetmobile2021

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AffichagePlanteActivity : AppCompatActivity() {

    val model by lazy { ViewModelProvider(this).get(PlanteViewModel::class.java)}
    lateinit var adapter : PlanteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_affichage_plante)

        val recyclerView = findViewById(R.id.recycler) as RecyclerView
        adapter = PlanteAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        model.getPlante()
        model.plantes.observe(this){
            adapter.setPlante(it)

        }

    }

    fun supprimerPlante(id:Int,position:Int){
        model.deletePlante(id)

    }
}