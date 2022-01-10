package com.example.projetmobile2021

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.projetmobile2021.databinding.ActivityEditPlanteBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import android.widget.Spinner
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import java.io.File


class EditPlanteActivity : AppCompatActivity() {

    val model by lazy { ViewModelProvider(this).get(PlanteViewModel::class.java)}
    private lateinit var binding: ActivityEditPlanteBinding

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

    override fun onSaveInstanceState(outState: Bundle){
        super.onSaveInstanceState(outState)
        var nom = binding.nom.text.toString()
        var nomLatin = binding.nomLatin.text.toString()
        var freqD1 = binding.dateDebut1.text.toString()
        var freqF1 = binding.dateFin1.text.toString()
        var freqD2 = binding.dateDebut2.text.toString()
        var freqF2 = binding.dateFin2.text.toString()
        var freqD3 = binding.dateDebut3.text.toString()
        var freqF3 = binding.dateFin3.text.toString()
        var freq1 = binding.frequence1.selectedItem.toString()
        var freq2 = binding.frequence2.selectedItem.toString()
        var freq3 = binding.frequence3.selectedItem.toString()
        var uriSave = localUri.toString()
        outState.putString("nom", nom)
        outState.putString("nomLatin", nomLatin)
        outState.putString("freqD1", freqD1)
        outState.putString("freqF1", freqF1)
        outState.putString("freqD2", freqD2)
        outState.putString("freqF2", freqF2)
        outState.putString("freqD3", freqD3)
        outState.putString("freqF3", freqF3)
        outState.putString("freq1", freq1)
        outState.putString("freq2", freq2)
        outState.putString("freq3", freq3)
        outState.putString("uriSave", uriSave)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updatePlante(view: android.view.View) {
        var id = this.intent.getIntExtra("id", -1);
        model.getPlante(id)
        listeDateFreqDeb = arrayListOf()
        listeDateFreqFin = arrayListOf()
        listeFreq = arrayListOf()
        var check = false
        model.planteSelect.observe(this) {
            if (check == false) {
                var plante = model.planteSelect.value!![0]
                var nom: EditText = binding.nom
                var nomLatin: EditText = binding.nomLatin

                var dateFreqDeb: TextView = binding.dateDebut1
                var dateFreqFin: TextView = binding.dateFin1
                var freq: Spinner = findViewById(R.id.frequence1)
                if (!(dateFreqDeb.text.toString() == "" || dateFreqDeb.text.toString() == null ||
                            dateFreqFin.text.toString() == "" || dateFreqFin.text.toString() == null ||
                            freq.selectedItem.toString() == "" || freq.selectedItem.toString() == null)
                ) {
                    var debut: List<String> = dateFreqDeb.text.split("/").map { it }
                    var fin: List<String> = dateFreqFin.text.split("/").map { it }
                    listeDateFreqDeb.add(
                        LocalDate.of(
                            debut[2].toInt(),
                            debut[1].toInt(),
                            debut[0].toInt()
                        )
                    )
                    Log.i("test2 Jour", debut[0].toString())
                    listeDateFreqFin.add(
                        LocalDate.of(
                            fin[2].toInt(),
                            fin[1].toInt(),
                            fin[0].toInt()
                        )
                    )
                    listeFreq.add(freq.selectedItem.toString().toInt())
                }

                dateFreqDeb = binding.dateDebut2
                dateFreqFin = binding.dateFin2
                freq = findViewById(R.id.frequence2)
                if (!(dateFreqDeb.text.toString() == "" || dateFreqDeb.text.toString() == null ||
                            dateFreqFin.text.toString() == "" || dateFreqFin.text.toString() == null ||
                            freq.selectedItem.toString() == "" || freq.selectedItem.toString() == null)
                ) {
                    var debut: List<String> = dateFreqDeb.text.split("/").map { it }
                    var fin: List<String> = dateFreqFin.text.split("/").map { it }
                    listeDateFreqDeb.add(
                        LocalDate.of(
                            debut[2].toInt(),
                            debut[1].toInt(),
                            debut[0].toInt()
                        )
                    )
                    listeDateFreqFin.add(
                        LocalDate.of(
                            fin[2].toInt(),
                            fin[1].toInt(),
                            fin[0].toInt()
                        )
                    )
                    listeFreq.add(freq.selectedItem.toString().toInt())
                }

                dateFreqDeb = binding.dateDebut3
                dateFreqFin = binding.dateFin3
                freq = findViewById(R.id.frequence3)
                if (!(dateFreqDeb.text.toString() == "" || dateFreqDeb.text.toString() == null ||
                            dateFreqFin.text.toString() == "" || dateFreqFin.text.toString() == null ||
                            freq.selectedItem.toString() == "" || freq.selectedItem.toString() == null)
                ) {
                    var debut: List<String> = dateFreqDeb.text.split("/").map { it }
                    var fin: List<String> = dateFreqFin.text.split("/").map { it }
                    listeDateFreqDeb.add(
                        LocalDate.of(
                            debut[2].toInt(),
                            debut[1].toInt(),
                            debut[0].toInt()
                        )
                    )
                    listeDateFreqFin.add(
                        LocalDate.of(
                            fin[2].toInt(),
                            fin[1].toInt(),
                            fin[0].toInt()
                        )
                    )
                    listeFreq.add(freq.selectedItem.toString().toInt())
                }

                if (nom.text.toString() == "" || nom.text.toString() == null) {
                    AlertDialog.Builder(this).setMessage("Ajouter le nom de la plante")
                        .setCancelable(true).show()
                } else if (listeDateFreqDeb.size == 0) {
                    AlertDialog.Builder(this).setMessage("Ajouter des fréquence d'arrosage")
                        .setCancelable(true).show()
                } else if (localUri == null) {
                    AlertDialog.Builder(this).setMessage("Ajouter une image pour la plante")
                        .setCancelable(true).show()
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        plante.nom = nom.text.toString()
                        Log.i("test4", plante.nom)
                        plante.nomLatin = nomLatin.text.toString()
                        plante.dateFrequenceDebut = listeDateFreqDeb
                        plante.dateFrequenceFin = listeDateFreqFin
                        plante.frequence = listeFreq
                        plante.uriImage = localUri.toString()
                        model.updatePlante(plante)

                        AlertDialog.Builder(this).setMessage("Mise a jour de la plante effectué")
                            .setCancelable(true).show()

                    }
                }
            }
            check = true
        }
        finish();
        startActivity(getIntent());
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_plante)
        binding = ActivityEditPlanteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var id = this.intent.getIntExtra("id", -1);
        val spinner1 = binding.frequence1
        ArrayAdapter.createFromResource(
            this, R.array.freq_arrosage_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner1.adapter = adapter
        }
        val spinner2 = binding.frequence2
        ArrayAdapter.createFromResource(
            this, R.array.freq_arrosage_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner2.adapter = adapter
        }
        val spinner3 = binding.frequence3
        ArrayAdapter.createFromResource(
            this, R.array.freq_arrosage_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner3.adapter = adapter
        }

        val selectButton = binding.choisirImage
        selectButton.setOnClickListener{
            getContent.launch("image/*")
        }
        model.getPlante(id)
        model.planteSelect.observe(this){
            var plante = model.planteSelect.value!![0]
            var nom : EditText = binding.nom
            var nomLatin : EditText = binding.nomLatin
            nom.setText(plante.nom)
            nomLatin.setText(plante.nomLatin)
            binding.textViewFreq.setText("Frequence (au max 3) :")
            val myUri = Uri.parse(plante.uriImage)
            findViewById<ImageView>(R.id.imagePlante).setImageURI(myUri)
            localUri = myUri
            listeDateFreqDeb = plante.dateFrequenceDebut as MutableList<LocalDate>
            listeDateFreqFin = plante.dateFrequenceFin as MutableList<LocalDate>
            listeFreq = plante.frequence as MutableList<Int>

            setSpinText(binding.frequence1,listeFreq[0].toString())
            binding.dateDebut1.setText(listeDateFreqDeb[0].dayOfMonth.toString() +"/"+
                    listeDateFreqDeb[0].monthValue + "/" + listeDateFreqDeb[0].year)
            binding.dateFin1.setText(listeDateFreqFin[0].dayOfMonth.toString() +"/"+
                    listeDateFreqFin[0].monthValue + "/" + listeDateFreqFin[0].year)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (listeFreq.size > 1) {
                    if (listeFreq[1].toString() != "") {
                        setSpinText(binding.frequence2, listeFreq[1].toString())
                        binding.dateDebut2.setText(
                            listeDateFreqDeb[1].dayOfMonth.toString() + "/" +
                                    listeDateFreqDeb[1].monthValue + "/" + listeDateFreqDeb[1].year
                        )
                        binding.dateFin2.setText(
                            listeDateFreqFin[1].dayOfMonth.toString() + "/" +
                                    listeDateFreqFin[1].monthValue + "/" + listeDateFreqFin[1].year
                        )
                    }
                }
                if (listeFreq.size > 2) {
                    if (listeFreq[2].toString() != "") {
                        setSpinText(binding.frequence3, listeFreq[2].toString())
                        binding.dateDebut3.setText(
                            listeDateFreqDeb[2].dayOfMonth.toString() + "/" +
                                    listeDateFreqDeb[2].monthValue + "/" + listeDateFreqDeb[2].year
                        )

                        binding.dateFin3.setText(
                            listeDateFreqFin[2].dayOfMonth.toString() + "/" +
                                    listeDateFreqFin[2].monthValue + "/" + listeDateFreqFin[2].year
                        )
                    }
                }

            }

            if (savedInstanceState != null){
                binding.nom.setText(savedInstanceState.getString("nom"))
                binding.nomLatin.setText(savedInstanceState.getString("nomLatin"))
                binding.dateDebut1.setText(savedInstanceState.getString("freqD1"))
                binding.dateFin1.setText(savedInstanceState.getString("freqF1"))
                binding.dateDebut2.setText(savedInstanceState.getString("freqD2"))
                binding.dateFin2.setText(savedInstanceState.getString("freqF2"))
                binding.dateDebut3.setText(savedInstanceState.getString("freqD3"))
                binding.dateFin3.setText(savedInstanceState.getString("freqF3"))
                val myUri = Uri.parse(savedInstanceState.getString("uriSave"))
                findViewById<ImageView>(R.id.imagePlante).setImageURI(myUri)
                localUri = myUri
            }


        }

    }

    fun supprimerValeur1(view: android.view.View){
        binding.dateDebut1.setText("")
        binding.dateFin1.setText("")
        setSpinText(binding.frequence1, "0")
    }
    fun supprimerValeur2(view: android.view.View){
        binding.dateDebut2.setText("")
        binding.dateFin2.setText("")
        setSpinText(binding.frequence2, "0")
    }
    fun supprimerValeur3(view: android.view.View){
        binding.dateDebut3.setText("")
        binding.dateFin3.setText("")
        setSpinText(binding.frequence3, "0")
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun chosirDateDebut(view: android.view.View) {
        val c= Calendar.getInstance()
        val annee = c.get(Calendar.YEAR)
        val mois = c.get(Calendar.MONTH)
        val jour = c.get(Calendar.DAY_OF_MONTH)
        val date : TextView = findViewById(R.id.dateDebut1)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view, annee, mois, jour->
            c.set(Calendar.YEAR,annee)
            c.set(Calendar.MONTH,mois)
            c.set(Calendar.DAY_OF_MONTH,jour)
            val myFormat ="dd/MM/yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.FRANCE)
            date.text = sdf.format(c.time)
        },annee,mois,jour)
        dpd.show()
    }
    fun chosirDateFin(view: android.view.View) {
        val c= Calendar.getInstance()
        val annee = c.get(Calendar.YEAR)
        val mois = c.get(Calendar.MONTH)
        val jour = c.get(Calendar.DAY_OF_MONTH)
        val date : TextView = findViewById(R.id.dateFin1)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view, annee, mois, jour->
            c.set(Calendar.YEAR,annee)
            c.set(Calendar.MONTH,mois)
            c.set(Calendar.DAY_OF_MONTH,jour)
            val myFormat ="dd/MM/yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.FRANCE)
            date.text = sdf.format(c.time)
        },annee,mois+1,jour)
        dpd.show()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun chosirDateDebut2(view: android.view.View) {
        val c= Calendar.getInstance()
        val annee = c.get(Calendar.YEAR)
        val mois = c.get(Calendar.MONTH)
        val jour = c.get(Calendar.DAY_OF_MONTH)
        val date : TextView = findViewById(R.id.dateDebut2)

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
    fun chosirDateFin2(view: android.view.View) {
        val c= Calendar.getInstance()
        val annee = c.get(Calendar.YEAR)
        val mois = c.get(Calendar.MONTH)
        val jour = c.get(Calendar.DAY_OF_MONTH)
        val date : TextView = findViewById(R.id.dateFin2)

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

    @RequiresApi(Build.VERSION_CODES.N)
    fun chosirDateDebut3(view: android.view.View) {
        val c= Calendar.getInstance()
        val annee = c.get(Calendar.YEAR)
        val mois = c.get(Calendar.MONTH)
        val jour = c.get(Calendar.DAY_OF_MONTH)
        val date : TextView = findViewById(R.id.dateDebut3)

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
    fun chosirDateFin3(view: android.view.View) {
        val c= Calendar.getInstance()
        val annee = c.get(Calendar.YEAR)
        val mois = c.get(Calendar.MONTH)
        val jour = c.get(Calendar.DAY_OF_MONTH)
        val date : TextView = findViewById(R.id.dateFin3)

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

    fun setSpinText(spin: Spinner, text: String?) {
        for (i in 0 until spin.adapter.count) {
            if (spin.adapter.getItem(i).toString().contains(text!!)) {
                spin.setSelection(i)
            }
        }
    }
}