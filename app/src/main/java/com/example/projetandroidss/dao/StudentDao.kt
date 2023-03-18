package com.example.projetandroidss.dao

import androidx.room.*
import com.example.projetandroidss.entities.Formation
import com.example.projetandroidss.entities.Initiator
import com.example.projetandroidss.entities.Student

@Dao
interface StudentDao {
    @Query("SELECT * FROM student")
    fun getAllStudent(): List<Student>

    @Query("SELECT * FROM student where id = :id")
    fun getById(id : Int): Student
    @Insert fun insertOne(student: Student) : Long

    @Insert fun insert(vararg student: Student)
    
}