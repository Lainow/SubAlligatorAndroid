package com.example.projetandroidss.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contents")
data class Content(
    @ColumnInfo(name = "session_id") val sessionId: Int,
    @ColumnInfo(name = "aptitude_id") val aptitudeId: Int
)

