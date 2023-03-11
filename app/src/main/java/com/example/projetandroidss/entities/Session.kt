package com.example.projetandroidss.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sessions")
data class Session(
    @PrimaryKey val id: Int,
    val date: String,
    @ColumnInfo(name = "formation_id") val formationId: Int,
    val deleted: Boolean
)