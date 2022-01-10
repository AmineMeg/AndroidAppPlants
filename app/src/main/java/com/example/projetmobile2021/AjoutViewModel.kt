package com.example.projetmobile2021

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class AjoutViewModel : ViewModel() {

    var listeDebut = MutableLiveData<MutableList<LocalDate>>()
    var listeFin = MutableLiveData<MutableList<LocalDate>>()
    var frequence = MutableLiveData<MutableList<Int>>()
    var uri = MutableLiveData<Uri>()
    var debut = MutableLiveData<String>()
    var fin = MutableLiveData<String>()

}