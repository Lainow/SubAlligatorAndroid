package com.example.projetandroidss.dao

import androidx.room.Query
import com.example.projetandroidss.entities.Participation

interface ParticipationDao {
    @Query("SELECT * FROM participations")
    fun getAllParticipation(): List<Participation>
}