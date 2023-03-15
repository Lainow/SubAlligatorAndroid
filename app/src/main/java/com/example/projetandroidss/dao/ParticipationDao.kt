package com.example.projetandroidss.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projetandroidss.entities.Participation

@Dao
interface ParticipationDao {
    @Query("SELECT * FROM participation")
    fun getAllParticipation(): List<Participation>

    @Insert
    fun insertOne(participation: Participation) : Long
}