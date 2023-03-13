package com.example.projetandroidss.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "aptitude",
    foreignKeys = [
    ForeignKey(
        entity = Skill::class,
        parentColumns = ["id"],
        childColumns = ["skillId"]
    )
])
data class Aptitude (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val skillId: Int,
    val deleted: Boolean
    )