package com.example.projetandroidss.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.projetandroidss.entities.Participation
import com.example.projetandroidss.entities.TrainingManager

@Dao
interface TrainingManagerDao {
    @Query("SELECT * FROM trainingManager")
    fun getAllTrainingManager(): List<TrainingManager>
}