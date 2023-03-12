package com.example.projetandroidss.dao

import androidx.room.Insert
import androidx.room.Query
import com.example.projetandroidss.entities.Level
import com.example.projetandroidss.entities.Participation
import com.example.projetandroidss.entities.Session

interface SessionDao {
    @Query("SELECT * FROM sessions")
    fun getAllSession(): List<Session>

    @Insert
    fun insertSession(vararg session: Session)
}