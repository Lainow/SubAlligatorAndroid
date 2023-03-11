package com.example.projetandroidss.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "formations")
data class Formation(
    @PrimaryKey val id: Int,
    val name: String,
    val deleted: Boolean

    )
