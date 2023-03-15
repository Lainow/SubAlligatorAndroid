package com.example.projetandroidss.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "content",
    foreignKeys = [
    ForeignKey(
        entity = Session::class,
        parentColumns = ["id"],
        childColumns = ["sessionId"]
    ),
    ForeignKey(
        entity = Aptitude::class,
        parentColumns = ["id"],
        childColumns = ["aptitudeId"]
    )
])
class Content(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val sessionId: Int,
    val aptitudeId: Int
)

