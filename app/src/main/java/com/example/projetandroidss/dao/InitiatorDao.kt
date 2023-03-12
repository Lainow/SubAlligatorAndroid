package com.example.projetandroidss.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projetandroidss.entities.Initiator

@Dao
interface InitiatorDao {
    @Query("SELECT * FROM initiator")
    fun getAllInitiator(): List<Initiator>

    @Insert fun insertInitiator(vararg initiator: Initiator)
}