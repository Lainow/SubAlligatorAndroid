package com.example.projetandroidss.entities

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Room
import com.example.projetandroidss.BDD
import com.google.gson.Gson
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

@Entity(tableName = "levels")
data class Level(
    @PrimaryKey val id: Int,
    val name: String,
    val deleted: Boolean
)

