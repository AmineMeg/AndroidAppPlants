package com.example.projetmobile2021

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Plante::class],version = 1)
abstract class PlanteBD : RoomDatabase() {

    abstract fun MyDAO(): MyDAO

    companion object{

        @Volatile
        private var INSTANCE: PlanteBD? = null

        fun getDatabase(context: Context): PlanteBD{
            if(INSTANCE != null)
                return INSTANCE!!
            val db = Room.databaseBuilder(
                context.applicationContext,
                PlanteBD::class.java, "pays"
            ).build()
            INSTANCE = db
            return INSTANCE!!
        }
    }

}