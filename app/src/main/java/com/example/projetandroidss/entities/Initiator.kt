package com.example.projetandroidss.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "initiator")
data class Initiator(
    @PrimaryKey val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val director: Boolean,
    val deleted: Boolean
)
