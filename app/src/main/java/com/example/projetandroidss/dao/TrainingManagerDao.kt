package com.example.projetandroidss.dao

import androidx.room.Query
import com.example.projetandroidss.entities.Participation
import com.example.projetandroidss.entities.TrainingManager

interface TrainingManagerDao {
    @Query("SELECT * FROM managers")
    fun getAllTrainingManager(): List<TrainingManager>
}