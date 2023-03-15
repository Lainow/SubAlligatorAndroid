package com.example.projetandroidss.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "session",
    foreignKeys = [
        ForeignKey(
            entity = Formation::class,
            parentColumns = ["id"],
            childColumns = ["formationId"]
        )
    ])
class Session(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val date: String,
    val formationId: Int,
    val deleted: Boolean
)