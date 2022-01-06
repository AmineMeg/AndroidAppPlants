package com.example.projetmobile2021

import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate

class ArrosageAdapter(arrosage:ArrosageActivity) : RecyclerView.Adapter<ArrosageAdapter.VH>() {

    var arrosageAct : ArrosageActivity = arrosage
    var plantesPourArrosage : List<Plante> = mutableListOf()

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        lateinit var plante : Plante
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val holder = VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_view_arrosage, parent, false)
        )
        return holder
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.plante=plantesPourArrosage[position]
        var plantes = holder.plante
        holder.itemView.findViewById<TextView>(R.id.nomPlante).text = holder.plante.nom
        holder.itemView.findViewById<TextView>(R.id.dernierArrosage).text = holder.plante.dernierArosage.toString()
        holder.itemView.findViewById<Button>(R.id.arrosage).setOnClickListener{
            for(j in 0..plantes.dateFrequenceDebut.size-1){
                if (plantes.dateFrequenceDebut[j].year == plantes.dateFrequenceFin[j].year) {
                    if(LocalDate.now().month>=plantes.dateFrequenceDebut[j].month &&
                        LocalDate.now().month<=plantes.dateFrequenceFin[j].month &&
                        LocalDate.now().dayOfMonth>=plantes.dateFrequenceDebut[j].dayOfMonth &&
                        LocalDate.now().dayOfMonth<=plantes.dateFrequenceFin[j].dayOfMonth){
                        plantes.prochainArosage = LocalDate.of(LocalDate.now().year, LocalDate.now().month,LocalDate.now().dayOfMonth + plantes.frequence[j])

                    }
                } else {
                    if (LocalDate.now().month >= plantes.dateFrequenceDebut[j].month &&
                        LocalDate.now().dayOfMonth >= plantes.dateFrequenceDebut[j].dayOfMonth
                        ||
                        LocalDate.now().month <= plantes.dateFrequenceFin[j].month &&
                        LocalDate.now().dayOfMonth <= plantes.dateFrequenceFin[j].dayOfMonth
                    ) {
                        plantes.prochainArosage = LocalDate.of(LocalDate.now().year, LocalDate.now().month,LocalDate.now().dayOfMonth + plantes.frequence[j])
                    }
                }
            }
            arrosageAct.updateDatePlante(plantesPourArrosage[position])
            notifyItemRemoved(position)
        }
        val localUri = Uri.parse( holder.plante.uriImage )
        holder.itemView.findViewById<ImageView>(R.id.imagePlante).setImageURI( localUri )
    }

    override fun getItemCount(): Int {
        return plantesPourArrosage.size
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun setPlante(plantes : List<Plante> ){
        val liste : MutableList<Plante> = mutableListOf()
        for (i in 0..plantes.size-1){
            if(plantes[i].prochainArosage.equals(LocalDate.now()))
                liste.add(plantes[i])
        }
        plantesPourArrosage=liste
        notifyDataSetChanged()
    }
}