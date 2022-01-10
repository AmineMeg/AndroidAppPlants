package com.example.projetmobile2021

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetmobile2021.databinding.ActivityArrosageBinding
import java.time.LocalDate

class ArrosageActivity : AppCompatActivity() {

    val model by lazy { ViewModelProvider(this).get(PlanteViewModel::class.java)}
    private lateinit var binding: ActivityArrosageBinding

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arrosage)

        binding = ActivityArrosageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView = binding.recycler
        val adapter = ArrosageAdapter(this, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        model.getPlante()

        model.plantes.observe(this){
            adapter.setPlante(it)

        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateDatePlante(plante:Plante){
        model.updatePlante(plante)
    }
}