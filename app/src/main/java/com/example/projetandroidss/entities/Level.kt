package com.example.projetandroidss.entities


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "level")

class Level(
    @PrimaryKey val id: Int,
    val name: String,
    val deleted: Boolean
)

