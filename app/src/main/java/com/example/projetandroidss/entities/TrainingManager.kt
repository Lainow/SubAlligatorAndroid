package com.example.projetandroidss.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "training_managers",
    foreignKeys = [
        ForeignKey(
            entity = Formation::class,
            parentColumns = ["initiatorId"],
            childColumns = ["initiator_id"]
        ),
        ForeignKey(
            entity = Formation::class,
            parentColumns = ["formationId"],
            childColumns = ["formation_id"]
        )
    ])
data class TrainingManager(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "initiator_id") val initiatorId: Int,
    @ColumnInfo(name = "formation_id") val formationId: Int
)