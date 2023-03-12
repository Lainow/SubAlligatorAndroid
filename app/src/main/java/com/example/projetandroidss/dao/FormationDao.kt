package com.example.projetandroidss.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projetandroidss.entities.Formation

@Dao
interface FormationDao {
    @Query("SELECT * FROM formation")
    fun getAllFormation(): List<Formation>

    @Insert fun insertInitiator(vararg formation: Formation)


}