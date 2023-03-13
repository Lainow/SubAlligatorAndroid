package com.example.projetandroidss.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projetandroidss.entities.Level
import com.example.projetandroidss.entities.Participation
import com.example.projetandroidss.entities.Status

@Dao
interface StatusDao {
    @Query("SELECT * FROM status")
    fun getAllStatus(): List<Status>

    @Insert
    fun insertStatus(vararg status: Status)
}