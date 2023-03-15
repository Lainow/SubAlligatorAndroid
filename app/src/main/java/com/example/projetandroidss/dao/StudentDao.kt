package com.example.projetandroidss.dao

import androidx.room.*
import com.example.projetandroidss.entities.Formation
import com.example.projetandroidss.entities.Student

@Dao
interface StudentDao {
    @Query("SELECT * FROM student")
    fun getAllStudent(): List<Student>
    @Insert fun insertOne(student: Student) : Long

    @Insert fun insert(vararg student: Student)
    
}