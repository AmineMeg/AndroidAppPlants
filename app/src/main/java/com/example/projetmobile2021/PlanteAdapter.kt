package com.example.projetmobile2021

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.itemView.findViewById<TextView>(R.id.dernierArrosage).text = holder.plante.dernierArosage.dayOfMonth.toString()+"/"+holder.plante.dernierArosage.monthValue
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.itemView.findViewById<TextView>(R.id.prochainArrosage).text = holder.plante.prochainArosage.dayOfMonth.toString()+"/"+holder.plante.prochainArosage.monthValue
        }
        val localUri = Uri.parse( holder.plante.uriImage )
        holder.itemView.findViewById<ImageView>(R.id.imagePlante).setImageURI( localUri )
        var text = "date fréqeuence arrosage = \n"
        for(i in 0..holder.plante.dateFrequenceDebut.size-1){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                text += " du : "+ holder.plante.dateFrequenceDebut[i].dayOfMonth+"/"+holder.plante.dateFrequenceDebut[i].monthValue +" au "+holder.plante.dateFrequenceFin[i].dayOfMonth+"/"+holder.plante.dateFrequenceFin[i].monthValue +" tout les "+holder.plante.frequence[i]+" jours" +"\n"
            } else {
            }
        }
        holder.itemView.findViewById<TextView>(R.id.frequenceArrosage).text = text
        holder.itemView.findViewById<ImageButton>(R.id.supprimer).setOnClickListener{
            activity.supprimerPlante(holder.plante.id)
        }
        holder.itemView.findViewById<Button>(R.id.editer).setOnClickListener{
            Log.i("test1", "let's go")
            val intent = Intent(activity, EditPlanteActivity::class.java)
            intent.putExtra("id", holder.plante.id);
            activity.startActivity(intent)
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