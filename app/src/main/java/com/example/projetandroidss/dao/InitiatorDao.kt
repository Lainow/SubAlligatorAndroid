package com.example.projetandroidss.dao

import androidx.room.*
import com.example.projetandroidss.entities.Formation
import com.example.projetandroidss.entities.Initiator

@Dao
interface InitiatorDao {
    @Query("SELECT * FROM initiator")
    fun getAllInitiator(): List<Initiator>

    @Query("SELECT * FROM initiator where email = :mail AND password = :password")
    fun getByMailPassword(mail: String, password: String): Initiator

    @Query("SELECT * FROM initiator where id = :id")
    fun getById(id : Int): Initiator

    @Insert fun insertOne(initiator: Initiator) : Long

    @Insert fun insert(vararg initiator: Initiator)

    @Update
    fun update(initiator: Initiator) : Int

    @Delete
    fun delete(vararg initiator: Initiator)

}