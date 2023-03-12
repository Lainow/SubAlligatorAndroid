package com.example.projetandroidss

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projetandroidss.dao.LevelDao
import com.example.projetandroidss.entities.Level

abstract class BDD : RoomDatabase() {
    //...
    companion object {
        private var instance: BDD? = null
        fun getInstance(context: Context): BDD {
            if (instance == null)
                instance = Room.databaseBuilder(
                    context,
                    BDD::class.java, "modules.sqlite"
                ).build()
            return instance!!
        }
    }
}