package com.example.projetandroidss.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "participation",
    foreignKeys = [
        ForeignKey(
            entity = Content::class,
            parentColumns = ["id"],
            childColumns = ["contentId"]
        ),
        ForeignKey(
            entity = Student::class,
            parentColumns = ["id"],
            childColumns = ["studentId"]
        ),
        ForeignKey(
            entity = Status::class,
            parentColumns = ["id"],
            childColumns = ["statusId"]
        )
    ])
class Participation(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val contentId: Int,
    val studentId: Int,
    var statusId: Int,
    val commentary: String?
)