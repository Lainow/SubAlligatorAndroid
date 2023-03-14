package com.example.projetandroidss.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projetandroidss.entities.Level

@Dao
interface LevelDao {
    @Query("SELECT * FROM level")
    fun getAllLevel(): LiveData<List<Level>>

    @Insert fun insert(vararg level: Level)

    @Insert fun insertOne(level: Level) : Long

    @Query("SELECT * FROM level WHERE id=:id")
    fun get(id: Long) : Level



}