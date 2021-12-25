package com.example.projetmobile2021

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AffichagePlanteActivity : AppCompatActivity() {

    val model by lazy { ViewModelProvider(this).get(PlanteViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_affichage_plante)

        val recyclerView = findViewById(R.id.recycler) as RecyclerView
        val adapter = PlanteAdapter()

        var listePlante : List<Plante> = mutableListOf()
        val bd = PlanteBD.getDatabase(application)
        val t= Thread{listePlante=bd.MyDAO().getAllPlantes()}
        t.start()
        t.join()
        Log.d("test",listePlante.toString())
        adapter.setPlante(listePlante)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }


}