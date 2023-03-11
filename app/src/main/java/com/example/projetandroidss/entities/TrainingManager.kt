package com.example.projetandroidss.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "training_managers")
data class TrainingManager(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "initiator_id") val initiatorId: Int,
    @ColumnInfo(name = "formation_id") val formationId: Int
)