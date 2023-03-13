package com.example.projetandroidss.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projetandroidss.entities.Level
import com.example.projetandroidss.entities.Participation
import com.example.projetandroidss.entities.Skill

@Dao
interface SkillDao {
    @Query("SELECT * FROM skill")
    fun getAllSkill(): List<Skill>

    @Insert
    fun insertSkill(vararg skill: Skill)
}