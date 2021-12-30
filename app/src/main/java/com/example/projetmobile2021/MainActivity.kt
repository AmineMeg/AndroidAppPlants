package com.example.projetmobile2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun pageAjoutPlante(view: android.view.View) {
        val intent = Intent(this, AjoutPlanteActivity::class.java)
        startActivity(intent)

    }

    fun afficherPäys(view: android.view.View) {
        val intent = Intent(this, AffichagePlanteActivity::class.java)
        startActivity(intent)
    }

    fun pageArrosagePlante(view: android.view.View) {
        val intent = Intent(this, ArrosageActivity::class.java)
        startActivity(intent)
    }
}