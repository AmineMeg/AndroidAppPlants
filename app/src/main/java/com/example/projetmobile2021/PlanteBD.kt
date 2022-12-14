package com.example.projetmobile2021

import android.content.Context
import androidx.room.*

@Database(entities = [Plante::class],version = 1)
@TypeConverters(Converters::class)
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
                PlanteBD::class.java, "plante"
            ).build()
            INSTANCE = db
            return INSTANCE!!
        }
    }

}