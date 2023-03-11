package com.example.projetandroidss.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skills")
data class Skill(
    @PrimaryKey val id: Int,
    val name: String,
    @ColumnInfo(name = "level_id") val levelId: Int,
    val deleted: Boolean
)