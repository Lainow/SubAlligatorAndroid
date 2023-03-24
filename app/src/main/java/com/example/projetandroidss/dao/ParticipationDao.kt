package com.example.projetandroidss.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projetandroidss.entities.Participation

@Dao
interface ParticipationDao {
    @Query("SELECT * FROM participation")
    fun getAllParticipation(): List<Participation>

    @Insert
    fun insertOne(participation: Participation) : Long

    @Query("SELECT * FROM participation where studentId = :studentId and contentId = :contentId")
    fun getByStudentAndContentId(studentId: Int, contentId: Int): List<Participation>

    @Query("SELECT * FROM participation where contentId = :contentId")
    fun getByContentId(contentId: Int): List<Participation>
}