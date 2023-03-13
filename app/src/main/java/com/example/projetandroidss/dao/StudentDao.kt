package com.example.projetandroidss.dao

import androidx.room.*
import com.example.projetandroidss.entities.Initiator
import com.example.projetandroidss.entities.Participation
import com.example.projetandroidss.entities.Student

@Dao
interface StudentDao {
    @Query("SELECT * FROM student")
    fun getAllStudent(): List<Student>

    @Insert
    fun insertStudent(vararg student: Student)
    
}