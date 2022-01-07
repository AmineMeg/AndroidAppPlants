package com.example.projetmobile2021

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class PlanteAdapter(planteAct : AffichagePlanteActivity) : RecyclerView.Adapter<PlanteAdapter.VH>() {

    private var activity = planteAct
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
        holder.itemView.findViewById<TextView>(R.id.nomLatin).text = holder.plante.nomLatin
        holder.itemView.findViewById<TextView>(R.id.dernierArrosage).text = holder.plante.dernierArosage.toString()
        val localUri = Uri.parse( holder.plante.uriImage )
        holder.itemView.findViewById<ImageView>(R.id.imagePlante).setImageURI( localUri )
        var freqTxt = "date fréqeuence arrosage = "
        for(i in 0..holder.plante.dateFrequenceDebut.size-1){
            Log.d("tst",i.toString())
            freqTxt+="du "+holder.plante.dateFrequenceDebut[i].toString() + "au" + holder.plante.dateFrequenceFin[i].toString() + " / "
        }
        holder.itemView.findViewById<TextView>(R.id.frequenceArrosage).text = freqTxt
        holder.itemView.findViewById<ImageButton>(R.id.supprimer).setOnClickListener{
            activity.supprimerPlante(holder.plante.id,position)
        }
    }

    override fun getItemCount(): Int {
        return allPlantes.size
    }

    fun setPlante(plantes : List<Plante> ){
        this.allPlantes = plantes
        notifyDataSetChanged()
    }
}