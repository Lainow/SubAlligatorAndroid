package com.example.projetandroidss.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "skill", foreignKeys = [
    ForeignKey(
        entity = Level::class,
        parentColumns = ["id"],
        childColumns = ["levelId"]
    )])
class Skill(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val levelId: Int,
    val deleted: Boolean
)