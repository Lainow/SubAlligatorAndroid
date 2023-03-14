package com.example.projetandroidss.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "initiator")
class Initiator(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val director: Boolean,
    val levelId : Int,
    val deleted: Boolean
)
