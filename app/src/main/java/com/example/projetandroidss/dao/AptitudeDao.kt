package com.example.projetandroidss.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projetandroidss.entities.Aptitude
import com.example.projetandroidss.entities.Initiator

@Dao
interface AptitudeDao {
    @Query("SELECT * FROM aptitude")
    fun getAllAptitude(): List<Aptitude>

    @Query("SELECT COUNT(*) FROM aptitude")
    fun count(): Int

    @Insert fun insertOne(aptitude: Aptitude) : Long

    @Insert fun insertAllAptitude(aptitudes: List<Aptitude>)
}