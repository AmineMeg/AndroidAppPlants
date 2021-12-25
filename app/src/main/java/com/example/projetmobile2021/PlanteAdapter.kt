package com.example.projetmobile2021

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlanteAdapter : RecyclerView.Adapter<PlanteAdapter.VH>() {

    private var allPlantes : List<Plante> = listOf()


    class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        lateinit var plante : Plante
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val holder = VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_view_plante,parent,false))
        return holder
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.plante=allPlantes[position]
        holder.itemView.findViewById<TextView>(R.id.nomPlante).text = holder.plante.nom

    }

    override fun getItemCount(): Int {
        return allPlantes.size
    }

    fun setPlante(plantes : List<Plante> ){
        this.allPlantes = plantes
        //sortedList.replaceAll(allPays)
        notifyDataSetChanged()
    }
}