package com.example.projetandroidss.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projetandroidss.entities.Level

@Dao
interface LevelDao {
    @Query("SELECT * FROM levels")
    fun getAllLevel(): List<Level>

    @Insert fun insertLevel(vararg level: Level)

}