package com.example.projetmobile2021

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Month
import java.util.*

class PlanteViewModel(application: Application) : AndroidViewModel(application) {

    val dao = PlanteBD.getDatabase(application).MyDAO()
    val TAG = "AddPaysViewModel"
    val plantes = MutableLiveData<List<Plante>>()
    var planteSelect = MutableLiveData<List<Plante>>()

    fun getPlante(){
        Thread{plantes.postValue(dao.getAllPlantes())}.start()
    }

    fun loadPartialName(nom: String) {
        Thread { planteSelect.postValue(dao.getPlantePartialNom(nom)) }.start()
    }

    fun deletePlante(id:Int){
        Thread{dao.deletePlante(id)
            getPlante()}.start()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addPlante(nom:String,nomLatin:String,dateDeb:List<LocalDate>,dateFin:List<LocalDate>,freq:List<Int>,uri:String){
        Thread{
            Log.i("tst", dateDeb.size.toString())
            var freqActuel = 0;
            for (i in 0..(dateDeb.size-1)) {
                if (dateDeb[i].year == dateFin[i].year) {
                    if(LocalDate.now().month>=dateDeb[i].month &&
                        LocalDate.now().month<=dateFin[i].month &&
                        LocalDate.now().dayOfMonth>=dateDeb[i].dayOfMonth &&
                        LocalDate.now().dayOfMonth<=dateFin[i].dayOfMonth){

                            freqActuel = freq[i];

                    }
                } else {
                    if (LocalDate.now().month >= dateDeb[i].month &&
                        LocalDate.now().dayOfMonth >= dateDeb[i].dayOfMonth
                        ||
                        LocalDate.now().month <= dateFin[i].month &&
                        LocalDate.now().dayOfMonth <= dateFin[i].dayOfMonth
                    ) {
                        freqActuel = freq[i];
                    }
                }

                Log.i("tst2", freq[i].toString())
            }
            dao.insert(
                Plante(
                    id = 0, nom = nom, nomLatin = nomLatin,
                    dateFrequenceDebut = dateDeb,
                    dateFrequenceFin = dateFin ,
                    dernierArosage =  LocalDate.now(),
                    prochainArosage = LocalDate.now()/*.plusDays(freqActuel.toLong())*/,
                    frequence = freq,
                    uriImage = uri
                )
            )
        }.start()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updatePlante(plante:Plante){
        Thread{
            dao.updatePlante(Plante(
                id = plante.id,
                nom = plante.nom,
                nomLatin = plante.nomLatin,
                dateFrequenceDebut = plante.dateFrequenceDebut,
                dateFrequenceFin = plante.dateFrequenceFin,
                frequence = plante.frequence,
                dernierArosage = plante.dernierArosage,
                prochainArosage = plante.prochainArosage,
                uriImage = plante.uriImage
                )
            )
            getPlante()
            }.start()

    }

}