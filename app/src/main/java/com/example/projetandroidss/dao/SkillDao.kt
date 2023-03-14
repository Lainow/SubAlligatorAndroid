package com.example.projetandroidss.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projetandroidss.entities.Level
import com.example.projetandroidss.entities.Participation
import com.example.projetandroidss.entities.Skill

@Dao
interface SkillDao {
    @Query("SELECT * FROM skill")
    fun getAllSkill(): LiveData<List<Skill>>

    @Insert
    fun insertSkill(vararg skill: Skill)
}