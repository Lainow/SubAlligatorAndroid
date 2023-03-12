package com.example.projetandroidss.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.projetandroidss.entities.Initiator
import com.example.projetandroidss.entities.Participation
import com.example.projetandroidss.entities.Student

interface StudentDao {
    @Query("SELECT * FROM students")
    fun getAllStudent(): List<Student>

    @Insert
    fun insertStudent(vararg student: Student)

    @Update
    fun update(vararg student: Student)
    @Delete
    fun delete(vararg student: Student)
}