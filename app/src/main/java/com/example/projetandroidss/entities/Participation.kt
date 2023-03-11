package com.example.projetandroidss.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "participation")
class Participation(
    @PrimaryKey val id: Int,
    val contentId: Int,
    val studentId: Int,
    val statusId: Int,
    val commentary: String?
)