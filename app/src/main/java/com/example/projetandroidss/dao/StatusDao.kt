package com.example.projetandroidss.dao

import androidx.room.Insert
import androidx.room.Query
import com.example.projetandroidss.entities.Level
import com.example.projetandroidss.entities.Participation
import com.example.projetandroidss.entities.Status

interface StatusDao {
    @Query("SELECT * FROM statuses")
    fun getAllStatus(): List<Status>

    @Insert
    fun insertStatus(vararg status: Status)
}