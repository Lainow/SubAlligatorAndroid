package com.example.projetandroidss.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "status")
data class Status(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val color: String
)