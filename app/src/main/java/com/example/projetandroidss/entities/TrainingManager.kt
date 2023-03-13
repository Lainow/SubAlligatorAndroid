package com.example.projetandroidss.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "trainingManager",
    foreignKeys = [
        ForeignKey(
            entity = Formation::class,
            parentColumns = ["id"],
            childColumns = ["initiatorId"]
        ),
        ForeignKey(
            entity = Formation::class,
            parentColumns = ["id"],
            childColumns = ["formationId"]
        )
    ])
data class TrainingManager(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val initiatorId: Int,
    val formationId: Int
)