package com.example.projetandroidss.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projetandroidss.entities.ContainSkill

@Dao
interface ContainSkillDao {
    @Query("SELECT * FROM containSkill")
    fun getAllContainSkill(): List<ContainSkill>

    @Insert fun insertContaiSkill(vararg containSkill: ContainSkill)
}