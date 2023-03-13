package com.example.projetandroidss.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "contents",
    foreignKeys = [
    ForeignKey(
        entity = Session::class,
        parentColumns = ["sessionId"],
        childColumns = ["session_id"]
    ),
    ForeignKey(
        entity = Aptitude::class,
        parentColumns = ["aptitudeId"],
        childColumns = ["aptitude_id"]
    )
])
data class Content(
    @ColumnInfo(name = "session_id") val sessionId: Int,
    @ColumnInfo(name = "aptitude_id") val aptitudeId: Int
)

