package com.example.projetandroidss.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "containSkills")
data class ContainSkill(
    @ColumnInfo(name = "formation_id") val formationId: Int,
    @ColumnInfo(name = "skill_id") val skillId: Int
)
