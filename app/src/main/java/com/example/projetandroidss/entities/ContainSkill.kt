package com.example.projetandroidss.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "containSkill",
    foreignKeys = [
        ForeignKey(
            entity = Skill::class,
            parentColumns = ["id"],
            childColumns = ["skillId"]
        ),
        ForeignKey(
            entity = Formation::class,
            parentColumns = ["id"],
            childColumns = ["formationId"]
        )
    ])
class ContainSkill(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val formationId: Int,
    val skillId: Int
)
