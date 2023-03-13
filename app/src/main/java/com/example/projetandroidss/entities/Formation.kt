package com.example.projetandroidss.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "formation")
data class Formation(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val deleted: Boolean

    )
