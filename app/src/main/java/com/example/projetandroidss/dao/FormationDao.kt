package com.example.projetandroidss.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projetandroidss.entities.Formation
import com.example.projetandroidss.entities.Level

@Dao
interface FormationDao {
    @Query("SELECT * FROM formation")
    fun getAllFormation(): List<Formation>

    @Insert fun insertOne(formation: Formation) : Long

    @Insert fun insert(vararg formation: Formation)

    @Query("SELECT * FROM formation where id = :id")
    fun getById(id: Int): Formation



}