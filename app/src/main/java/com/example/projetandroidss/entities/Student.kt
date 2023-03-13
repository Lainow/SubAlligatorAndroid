package com.example.projetandroidss.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "student",
    foreignKeys = [
        ForeignKey(
            entity = Formation::class,
            parentColumns = ["id"],
            childColumns = ["formationId"]
        )
    ])
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val formationId: Int,
    val phone: String,
    val deleted: Boolean
)