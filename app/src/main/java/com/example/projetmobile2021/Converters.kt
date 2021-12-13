package com.example.projetmobile2021

import android.icu.util.UniversalTimeScale.toLong
import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimesTamp(value: Long):Date{
        return value.let{ Date(it)}

    }

    @TypeConverter
    fun dateToTimestamp(date: Date):Long{
        return date.time.toLong()

    }

    @TypeConverter
    fun fromString(list : String) : List<Date> {
        var listDate:List<Date> = arrayListOf()
        var listString : List<String> = list.split(",").map{it}
        var stringToLong : List<Long> = arrayListOf()

        for (i in 0..listString.size-1){
            stringToLong.toMutableList().add(listString.get(i).toLong())
            listDate.toMutableList().add(fromTimesTamp(stringToLong.get(i)))
        }

        return listDate
    }
    @TypeConverter
    fun fromListDateToString(listDate:List<Date>):String{
        var listString : List<String> = arrayListOf()

        for(i in 0..listDate.size-1){
            listString.toMutableList().add(dateToTimestamp(listDate.get(i)).toString())
        }
        return listString.joinToString(separator =",")
    }

    @TypeConverter
    fun fromStringToInt(listString : String):List<Int>{
        var listFreq : List<Int> = arrayListOf()
        var listString : List<String> = listString.split(",").map{it}

        for (i in 0..listString.size-1){
            listFreq.toMutableList().add(listString.get(i).toInt())
        }

        return listFreq
    }

    @TypeConverter
    fun fromListIntToString(listInt:List<Int>):String{
        var listString : List<String> = arrayListOf()
        for(i in 0..listInt.size-1){
            listString.toMutableList().add(listInt.get(i).toString())
        }

        return listString.joinToString(separator =",")
    }


}