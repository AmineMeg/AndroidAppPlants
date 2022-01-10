package com.example.projetmobile2021

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetmobile2021.databinding.ActivityAffichagePlanteBinding

class AffichagePlanteActivity : AppCompatActivity() {

    val model by lazy { ViewModelProvider(this).get(PlanteViewModel::class.java)}
    lateinit var adapter : PlanteAdapter
    private lateinit var binding: ActivityAffichagePlanteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAffichagePlanteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recycler
        adapter = PlanteAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        model.getPlante()
        model.plantes.observe(this){
            adapter.setPlante(it)
        }

        binding.recherchePlante.addTextChangedListener( object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                model.loadPartialName(p0.toString())
            }

        } )

        model.planteSelect.observe(this){
            adapter.setPlante(it)

        }

    }

    fun supprimerPlante(id:Int){
        model.deletePlante(id)

    }
}