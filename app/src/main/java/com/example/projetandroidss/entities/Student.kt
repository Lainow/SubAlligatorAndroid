package com.example.projetandroidss.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student(
    @PrimaryKey val id: Int,
    val name: String,
    @ColumnInfo(name = "formation_id") val formationId: Int,
    val phone: String,
    val deleted: Boolean
)