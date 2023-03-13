package com.example.projetandroidss.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "skills",
    foreignKeys = [
        ForeignKey(
            entity = Level::class,
            parentColumns = ["levelId"],
            childColumns = ["level_id"]
        )
    ])
data class Skill(
    @PrimaryKey val id: Int,
    val name: String,
    @ColumnInfo(name = "level_id") val levelId: Int,
    val deleted: Boolean
)