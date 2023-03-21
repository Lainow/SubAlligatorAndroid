package com.example.projetandroidss.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projetandroidss.entities.Level
import com.example.projetandroidss.entities.Participation
import com.example.projetandroidss.entities.Session

@Dao
interface SessionDao {
    @Query("SELECT * FROM session")
    fun getAllSession(): List<Session>

    @Insert fun insertOne(session: Session) : Long

    @Query("SELECT * FROM session where formationId = :formationId")
    fun getByFormationId(formationId: Int): List<Session>

    @Query("SELECT * FROM session where id = :sessionId")
    fun getByID(sessionId: Int): Session
}