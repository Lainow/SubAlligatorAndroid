package com.example.projetandroidss.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "participation",
    foreignKeys = [
        ForeignKey(
            entity = Content::class,
            parentColumns = ["contentId"],
            childColumns = ["content_id"]
        ),
        ForeignKey(
            entity = Student::class,
            parentColumns = ["studentId"],
            childColumns = ["student_id"]
        ),
        ForeignKey(
            entity = Status::class,
            parentColumns = ["statusId"],
            childColumns = ["status_id"]
        )
    ])
class Participation(
    @PrimaryKey val id: Int,
    val contentId: Int,
    val studentId: Int,
    val statusId: Int,
    val commentary: String?
)