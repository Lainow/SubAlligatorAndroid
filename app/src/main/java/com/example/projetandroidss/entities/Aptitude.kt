package com.example.projetandroidss.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aptitude")
data class Aptitude (
    @PrimaryKey
    val id: Int,
    val name: String,
    val skillId: Int,
    val deleted: Boolean
    )