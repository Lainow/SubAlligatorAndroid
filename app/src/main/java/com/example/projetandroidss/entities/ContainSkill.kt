package com.example.projetandroidss.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "containSkills",
    foreignKeys = [
        ForeignKey(
            entity = Skill::class,
            parentColumns = ["skillId"],
            childColumns = ["skill_id"]
        ),
        ForeignKey(
            entity = Formation::class,
            parentColumns = ["formationId"],
            childColumns = ["formation_id"]
        )
    ])
data class ContainSkill(
    @ColumnInfo(name = "formation_id") val formationId: Int,
    @ColumnInfo(name = "skill_id") val skillId: Int
)
