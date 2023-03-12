package com.example.projetandroidss.dao

import androidx.room.*
import com.example.projetandroidss.entities.Initiator

@Dao
interface InitiatorDao {
    @Query("SELECT * FROM initiator")
    fun getAllInitiator(): List<Initiator>

    @Insert fun insertInitiator(vararg initiator: Initiator)

    @Update
    fun update(vararg initiator: Initiator)
    @Delete
    fun delete(vararg initiator: Initiator)

}