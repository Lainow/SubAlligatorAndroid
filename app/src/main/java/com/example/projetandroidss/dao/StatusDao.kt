package com.example.projetandroidss.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projetandroidss.entities.Level
import com.example.projetandroidss.entities.Participation
import com.example.projetandroidss.entities.Status

@Dao
interface StatusDao {
    @Query("SELECT * FROM status")
    fun getAllStatus(): LiveData<List<Status>>

    @Insert fun insert(vararg status: Status)

    @Insert fun insertOne(status: Status) : Long

    @Query("SELECT * FROM status where id = :id")
    fun getById(id: Int): Status

}