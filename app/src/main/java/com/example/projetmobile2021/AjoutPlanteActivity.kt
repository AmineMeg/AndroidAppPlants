package com.example.projetmobile2021

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import com.example.projetmobile2021.databinding.ActivityAjoutPlanteBinding
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


class AjoutPlanteActivity : AppCompatActivity() {

    val model by lazy { ViewModelProvider(this).get(PlanteViewModel::class.java)}
    private lateinit var binding: ActivityAjoutPlanteBinding

    lateinit var listeDateFreqDeb : MutableList<LocalDate>
    lateinit var listeDateFreqFin : MutableList<LocalDate>
    lateinit var listeFreq : MutableList<Int>
    var localUri : Uri? = null

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent())
    { uri: Uri? ->
        if(uri == null) return@registerForActivityResult
        val inputStream = getContentResolver().openInputStream(uri)
        val fileNamePrefix = "plante"
        val preferences = getSharedPreferences("numImage", Context.MODE_PRIVATE)
        val numImage = preferences.getInt("numImage",1)
        val fileName = "$fileNamePrefix$numImage"
        val file = File(this.filesDir, fileName)
        val outputStream = file.outputStream()

        preferences.edit().putInt("numImage",numImage+1).commit()

        inputStream?.copyTo(outputStream)

        localUri = file.toUri()
        outputStream.close()
        inputStream?.close()

       findViewById<ImageView>(R.id.imagePlante).setImageURI(localUri)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAjoutPlanteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listeDateFreqDeb = arrayListOf()
        listeDateFreqFin = arrayListOf()
        listeFreq = arrayListOf()

        val selectButton = binding.choisirImage
        selectButton.setOnClickListener{
            getContent.launch("image/*")
        }
    }

    fun ajoutPlante(view: android.view.View) {

        var nom : EditText = binding.nom
        var nomLatin : EditText = binding.nomLatin
        if(nom.text.toString()==""||nom.text.toString()==null){
            AlertDialog.Builder(this).setMessage("Ajouter le nom de la plante")
                .setCancelable(true).show()
        }else if(listeDateFreqDeb.size==0){
            AlertDialog.Builder(this).setMessage("Ajouter des fréquence d'arrosage")
                .setCancelable(true).show()
        }else if(localUri==null){
            AlertDialog.Builder(this).setMessage("Ajouter une image pour la plante")
                .setCancelable(true).show()
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                model.addPlante(
                    nom.text.toString(),
                    nomLatin.text.toString(),
                    listeDateFreqDeb,
                    listeDateFreqFin,
                    listeFreq,
                    localUri.toString()
                )
            }
            AlertDialog.Builder(this).setMessage("Ajout d'une plante effectué")
                .setCancelable(true).show()
            nom.setText("")
            nomLatin.setText("")
            binding.textViewFreq.setText("Frequence (au max 3) :")
            localUri = null
            binding.imagePlante.setImageURI(localUri)
            listeDateFreqDeb = arrayListOf()
            listeDateFreqFin = arrayListOf()
            listeFreq = arrayListOf()

        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun ajoutFreqArrosage(view: android.view.View) {
        if(listeDateFreqDeb.size<=2) {
            var dateFreqDeb: TextView = binding.dateDebut
            var dateFreqFin: TextView = binding.dateFin
            var freq: EditText = findViewById(R.id.frequence)
            if(!(dateFreqDeb.text.toString()==""||dateFreqDeb.text.toString()==null||dateFreqFin.text.toString()==""||dateFreqFin.text.toString()==null||freq.text.toString()==""||freq.text.toString()==null)) {
                var debut: List<String> = dateFreqDeb.text.split("/").map { it }
                var fin: List<String> = dateFreqFin.text.split("/").map { it }
                listeDateFreqDeb.add(
                    LocalDate.of(
                        debut[2].toInt(),
                        debut[1].toInt(),
                        debut[0].toInt()
                    )
                )
                listeDateFreqFin.add(LocalDate.of(fin[2].toInt(), fin[1].toInt(), fin[0].toInt()))
                listeFreq.add(freq.text.toString().toInt())
                dateFreqDeb.setText("")
                dateFreqFin.setText("")
                freq.setText("")
                var textFreq =binding.textViewFreq
                var text : String = textFreq.text.toString() + " du : "+debut[0]+"/"+debut[1] +" au "+fin[0]+"/"+fin[1] +"\n"
                textFreq.setText(text)
            }else{
                AlertDialog.Builder(this).setMessage("Remplissez toutes les infos pour la fréquence d'arrosage")
                    .setCancelable(true).show()
            }
        }else{
            AlertDialog.Builder(this).setMessage("Vous ne pouvez ajouter que 3 frequences d'arrosage")
                .setCancelable(true).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun chosirDateDebut(view: android.view.View) {
        val c= Calendar.getInstance()
        val annee = c.get(Calendar.YEAR)
        val mois = c.get(Calendar.MONTH)
        val jour = c.get(Calendar.DAY_OF_MONTH)
        val date : TextView = findViewById(R.id.dateDebut)

        val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view,annee,mois,jour->
            c.set(Calendar.YEAR,annee)
            c.set(Calendar.MONTH,mois)
            c.set(Calendar.DAY_OF_MONTH,jour)
           val myFormat ="dd/MM/yyyy"
            val sdf = SimpleDateFormat(myFormat,Locale.FRANCE)
            date.text = sdf.format(c.time)
        },annee,mois,jour)
        dpd.show()
    }
    fun chosirDateFin(view: android.view.View) {
        val c= Calendar.getInstance()
        val annee = c.get(Calendar.YEAR)
        val mois = c.get(Calendar.MONTH)
        val jour = c.get(Calendar.DAY_OF_MONTH)
        val date : TextView = findViewById(R.id.dateFin)

        val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view,annee,mois,jour->
            c.set(Calendar.YEAR,annee)
            c.set(Calendar.MONTH,mois)
            c.set(Calendar.DAY_OF_MONTH,jour)
            val myFormat ="dd/MM/yyyy"
            val sdf = SimpleDateFormat(myFormat,Locale.FRANCE)
            date.text = sdf.format(c.time)
        },annee,mois+1,jour)
        dpd.show()
    }


}