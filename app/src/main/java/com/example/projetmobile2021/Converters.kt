package com.example.projetmobile2021

import android.icu.util.UniversalTimeScale.toLong
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Converters {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromTimesTamp(value: String): LocalDate {
        return LocalDate.parse(value, DateTimeFormatter.ISO_DATE)

    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate):String{

        return date.toString()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromString(list : String) : List<LocalDate> {
        var listDate:MutableList<LocalDate> = arrayListOf()
        var listString : List<String> = list.split(",").map{it}

        Log.i("date","ICIIIIIIIIIIIIIIII"+list)

        for (i in 0..listString.size-1){
            listDate.add(fromTimesTamp(listString.get(i)))
        }

        return listDate
    }
    @TypeConverter
    fun fromListDateToString(listDate:List<LocalDate>):String{
        var listString : MutableList<String> = arrayListOf()
        for(i in 0..listDate.size-1){
            listString.add(dateToTimestamp(listDate.get(i)))
        }
        return listString.joinToString(separator =",")
    }

    @TypeConverter
    fun fromStringToInt(listString : String):List<Int>{
        var listFreq : MutableList<Int> = arrayListOf()
        var listString : List<String> = listString.split(",").map{it}

        for (i in 0..listString.size-1){
            listFreq.add(listString.get(i).toInt())
        }

        return listFreq
    }

    @TypeConverter
    fun fromListIntToString(listInt:List<Int>):String{
        var listString : MutableList<String> = arrayListOf()
        for(i in 0..listInt.size-1){
            listString.add(listInt.get(i).toString())
        }

        return listString.joinToString(separator =",")
    }


}