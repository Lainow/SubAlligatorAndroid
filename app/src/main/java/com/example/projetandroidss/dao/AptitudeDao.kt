package com.example.projetandroidss.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projetandroidss.entities.Aptitude

@Dao
interface AptitudeDao {
    @Query("SELECT * FROM aptitude")
    fun getAllAptitude(): List<Aptitude>

    @Insert fun insertAptitude(vararg aptitude: Aptitude)

    @Insert fun insertAllAptitude(aptitude: List<Aptitude>)
}